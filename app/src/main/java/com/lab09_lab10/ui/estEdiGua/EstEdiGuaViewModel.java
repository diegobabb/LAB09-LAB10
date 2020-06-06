package com.lab09_lab10.ui.estEdiGua;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.lab09_lab10.R;

public class EstEdiGuaViewModel extends ViewModel {

    private MutableLiveData<EstEdiGuaFormState> estEdiGuaFormState;

    public EstEdiGuaViewModel() {
        this.estEdiGuaFormState = new MutableLiveData<>();
    }

    public LiveData<EstEdiGuaFormState> getEstEdiGuaFormState() {
        return estEdiGuaFormState;
    }

    public void dataChanged(@Nullable String cedula, @Nullable String nombre, @Nullable String apellidos, @Nullable String edad) {
        EstEdiGuaFormState formState = new EstEdiGuaFormState();
        boolean dataValid = true;
        if (isTextInvalid(nombre)) {
            formState.setNombreError(R.string.invalid_username);
            dataValid = false;
        }
        if (isTextInvalid(apellidos)) {
            formState.setApellidosError(R.string.invalid_username);
            dataValid = false;
        }
        if (isCedulaInvalid(cedula)) {
            formState.setCedulaError(R.string.invalid_username);
            dataValid = false;
        }
        if (isTextInvalid(edad)) {
            formState.setEdadError(R.string.invalid_username);
            dataValid = false;
        }
        formState.setDataValid(dataValid);
        estEdiGuaFormState.setValue(formState);
    }

    private boolean isTextInvalid(String username) {
        if (username == null)
            return true;
        return username.trim().isEmpty();
    }

    private boolean isCedulaInvalid(String cedula){
        if (cedula == null)
            return true;
        return cedula.length() != 9;
    }
}
