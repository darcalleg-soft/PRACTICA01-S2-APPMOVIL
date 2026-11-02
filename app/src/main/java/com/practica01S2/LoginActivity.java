package com.practica01S2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    // Credenciales fijas de prueba (login simple, sin base de datos)
    private static final String USUARIO_VALIDO = "admin";
    private static final String CLAVE_VALIDA = "1234";

    public static final String EXTRA_USUARIO = "usuario";

    private TextInputLayout tilUsuario, tilClave;
    private TextInputEditText etUsuario, etClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        tilUsuario = findViewById(R.id.tilUsuario);
        tilClave = findViewById(R.id.tilClave);
        etUsuario = findViewById(R.id.etUsuario);
        etClave = findViewById(R.id.etClave);
        Button btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(v -> iniciarSesion());
    }

    private void iniciarSesion() {
        String usuario = etUsuario.getText() == null ? "" : etUsuario.getText().toString().trim();
        String clave = etClave.getText() == null ? "" : etClave.getText().toString();

        tilUsuario.setError(null);
        tilClave.setError(null);

        boolean valido = true;
        if (usuario.isEmpty()) {
            tilUsuario.setError(getString(R.string.error_usuario_vacio));
            valido = false;
        }
        if (clave.isEmpty()) {
            tilClave.setError(getString(R.string.error_clave_vacia));
            valido = false;
        }
        if (!valido) return;

        if (usuario.equals(USUARIO_VALIDO) && clave.equals(CLAVE_VALIDA)) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_USUARIO, usuario);
            startActivity(intent);
            finish(); // evita volver al login con el botón atrás
        } else {
            Toast.makeText(this, R.string.error_credenciales, Toast.LENGTH_SHORT).show();
            etClave.setText("");
        }
    }
}
