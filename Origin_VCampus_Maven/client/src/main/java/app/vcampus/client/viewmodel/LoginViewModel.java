package app.vcampus.client.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import lombok.extern.slf4j.Slf4j;

public class LoginViewModel {
    public final StringProperty username = new SimpleStringProperty("");
    public final StringProperty password = new SimpleStringProperty("");
    public final BooleanProperty loginState = new SimpleBooleanProperty(false);
    public final BooleanProperty showMessage = new SimpleBooleanProperty(false);
    public final StringProperty serverAddress = new SimpleStringProperty("127.0.0.1:9091");

    public void login() {
        // Reset showMessage state
        showMessage.set(false);

        Task<Boolean> loginTask = new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                if (!app.vcampus.client.repository.FakeRepository.isConnected) {
                    String[] serverAddr = serverAddress.get().split(":");
                    app.vcampus.client.net.NettyHandler handler = app.vcampus.client.Application.connect(serverAddr[0], Integer.parseInt(serverAddr[1]));
                    app.vcampus.client.repository.FakeRepository.handler = handler;
                    app.vcampus.client.repository.FakeRepository.isConnected = true;
                }

                return app.vcampus.client.repository.FakeRepository.login(username.get(), password.get());
            }
        };

        loginTask.setOnSucceeded(event -> {
            boolean success = loginTask.getValue();
            if (success) {
                loginState.set(true);
            } else {
                password.set("");
                showMessage.set(true);
            }
        });

        loginTask.setOnFailed(event -> {
            app.vcampus.client.repository.FakeRepository.isConnected = false;
            password.set("");
            showMessage.set(true);
        });

        new Thread(loginTask).start();
    }
}