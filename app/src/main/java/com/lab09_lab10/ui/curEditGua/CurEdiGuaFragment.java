package com.lab09_lab10.ui.curEditGua;

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
import com.lab09_lab10.ui.estEdiGua.EstEdiGuaFragment;

import java.util.Objects;

public class CurEdiGuaFragment extends Fragment implements View.OnClickListener {

    private CurEdiGuaViewModel mViewModel;
    Button button_guardar;
    ProgressBar progressBar;
    TextInputLayout textInputLayout_codigo, textInputLayout_descripcion, textInputLayout_creditos;
    TextInputEditText codigo, descripcion, creditos;
    TextWatcher afterTextChangedListener;

    public static CurEdiGuaFragment newInstance() {
        return new CurEdiGuaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cur_edi_gua, container, false);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mViewModel = ViewModelProviders.of(this).get(CurEdiGuaViewModel.class);
        View root = getView();
        this.mViewModel.getCurEdiGuaFormState().observe(this, new Observer<CurEdiGuaFormState>() {
            @Override
            public void onChanged(@Nullable CurEdiGuaFormState curEdiGuaFormState) {
                if (curEdiGuaFormState == null)
                    return;
                button_guardar.setEnabled(curEdiGuaFormState.isDataValid());
                textInputLayout_codigo.setError((curEdiGuaFormState.getCodigoError() != null ? getString(curEdiGuaFormState.getCodigoError()): null));
                textInputLayout_descripcion.setError((curEdiGuaFormState.getDescripcionError() != null ? getString(curEdiGuaFormState.getDescripcionError()) : null ));
                textInputLayout_creditos.setError((curEdiGuaFormState.getCreditosError() != null ? getString(curEdiGuaFormState.getCreditosError()) : null));
            }
        });
        assert root != null;
        this.codigo = root.findViewById(R.id.codigo);
        this.descripcion = root.findViewById(R.id.descripcion);
        this.creditos = root.findViewById(R.id.creditos);

        this.button_guardar = root.findViewById(R.id.button_guardar);
        this.textInputLayout_codigo = root.findViewById(R.id.textInputLayout_codigo);
        this.textInputLayout_descripcion = root.findViewById(R.id.textInputLayout_descripcion);
        this.textInputLayout_creditos = root.findViewById(R.id.textInputLayout_creditos);

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
                mViewModel.dataChanged(codigo.getText().toString(),
                        descripcion.getText().toString(),
                        creditos.getText().toString());
            }
        };

        this.button_guardar.setOnClickListener(this);
        this.codigo.addTextChangedListener(afterTextChangedListener);
        this.descripcion.addTextChangedListener(afterTextChangedListener);
        this.creditos.addTextChangedListener(afterTextChangedListener);

        FloatingActionButton floatingActionButton = Objects.requireNonNull(getActivity()).findViewById(R.id.fab);
        floatingActionButton.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        //TODO: Guardar o Editar el Estudiante antes de ir para atras y detener la progressBar
        NavHostFragment.findNavController(CurEdiGuaFragment.this)
                .navigate(R.id.action_CurEdiGuaFragment_to_nav_cursos);
    }
}
