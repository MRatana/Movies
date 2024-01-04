//package kh.edu.rupp.ite.movies.Activities;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import kh.edu.rupp.ite.movies.databinding.ActivityLoginBinding;
//
//public class LoginActivity extends AppCompatActivity {
//
//    public ActivityLoginBinding binding;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityLoginBinding.inflate(getLayoutInflater());
//
//        setContentView(binding.);
//
//        binding.btnLogin.setOnClickListener( v -> openMainActivity());
//
//        binding.txtSignup.setOnClickListener( v -> openSignupActivity());
//    }
//
//    private void openMainActivity(){
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }
//
//    private void openSignupActivity(){
//        Intent intent = new Intent(this, SignupActivity.class);
//        startActivity(intent);
//    }
//
//}
