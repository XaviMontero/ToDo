package com.example.yeshasprabhakar.todo.ucacue.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.content.Intent;
import android.util.Patterns;
import android.widget.Toast;

import com.example.yeshasprabhakar.todo.R;
import com.example.yeshasprabhakar.todo.model.Doctor;
import com.example.yeshasprabhakar.todo.model.User;
import com.example.yeshasprabhakar.todo.model.UuidMother;
import com.example.yeshasprabhakar.todo.ucacue.MainActivity;
import com.example.yeshasprabhakar.todo.ucacue.data.LoginRepository;
import com.example.yeshasprabhakar.todo.ucacue.data.Result;
import com.example.yeshasprabhakar.todo.ucacue.data.model.LoggedInUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;
    private DatabaseReference mDatabase;
    LoginViewModel(LoginRepository loginRepository) {

        this.loginRepository = loginRepository;
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public int login(String username, String password) {
        User user = new User(username, password );
         return getData(user);


    }





    private int getData(final User user) {
        final int[] e = {0};
        mDatabase.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i =0;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    User valor = postSnapshot.getValue(User.class);

                    if(valor.email.equals(user.email) && valor.username.equals(user.username) ){
                        e[0] = 1;
                        break;

                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message

                // ...
            }
        });
        return e[0];
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}