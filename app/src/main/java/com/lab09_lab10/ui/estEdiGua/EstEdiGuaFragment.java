package com.lab09_lab10.ui.estEdiGua;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.navigation.fragment.NavHostFragment;

import com.lab09_lab10.R;

import java.util.Objects;

public class EstEdiGuaFragment extends Fragment implements View.OnClickListener {

    EstEdiGuaViewModel mViewModel;
    Button button_guardar;
    ProgressBar progressBar;
    TextInputLayout textInputLayout_apellidos, textInputLayout_nombre, textInputLayout_cedula, textInputLayout_edad;
    TextInputEditText edad, apellidos, nombre, cedula;
    TextWatcher afterTextChangedListener;
    FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_est_edi_gua, container, false);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mViewModel = ViewModelProviders.of(this).get(EstEdiGuaViewModel.class);
        this.mViewModel.getEstEdiGuaFormState().observe(this, new Observer<EstEdiGuaFormState>() {
            @Override
            public void onChanged(@Nullable EstEdiGuaFormState estEdiGuaFormState) {
                if (estEdiGuaFormState == null)
                    return;
                button_guardar.setEnabled(estEdiGuaFormState.isDataValid());
                textInputLayout_nombre.setError((estEdiGuaFormState.getNombreError() != null ? getString(estEdiGuaFormState.getNombreError()): null));
                textInputLayout_apellidos.setError((estEdiGuaFormState.getApellidosError() != null ? getString(estEdiGuaFormState.getApellidosError()) : null ));
                textInputLayout_cedula.setError((estEdiGuaFormState.getCedulaError() != null ? getString(estEdiGuaFormState.getCedulaError()) : null));
                textInputLayout_edad.setError((estEdiGuaFormState.getEdadError() != null ? getString(estEdiGuaFormState.getEdadError()) : null));
            }
        });
        View root = getView();
        assert root != null;
        this.nombre = root.findViewById(R.id.nombre);
        this.apellidos = root.findViewById(R.id.apellidos);
        this.cedula = root.findViewById(R.id.cedula);
        this.edad = root.findViewById(R.id.edad);

        this.button_guardar = root.findViewById(R.id.button_guardar);
        this.textInputLayout_apellidos = root.findViewById(R.id.textInputLayout_apellidos);
        this.textInputLayout_nombre = root.findViewById(R.id.textInputLayout_nombre);
        this.textInputLayout_cedula = root.findViewById(R.id.textInputLayout_cedula);
        this.textInputLayout_edad = root.findViewById(R.id.textInputLayout_edad);

        this.afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                mViewModel.dataChanged(cedula.getText().toString(),
                        nombre.getText().toString(),
                        apellidos.getText().toString(),
                        edad.getText().toString());
            }
        };

        this.button_guardar.setOnClickListener(this);
        this.cedula.addTextChangedListener(afterTextChangedListener);
        this.nombre.addTextChangedListener(afterTextChangedListener);
        this.apellidos.addTextChangedListener(afterTextChangedListener);
        this.edad.addTextChangedListener(afterTextChangedListener);

        floatingActionButton = Objects.requireNonNull(getActivity()).findViewById(R.id.fab);
        floatingActionButton.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        //TODO: Guardar o Editar el Estudiante antes de ir para atras y detener la progressBar
        NavHostFragment.findNavController(EstEdiGuaFragment.this)
                .navigate(R.id.action_EstEdiGuaFragment_to_nav_estudiantes);
    }
}
