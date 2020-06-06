package com.lab09_lab10.ui.curEditGua;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.lab09_lab10.R;

public class CurEdiGuaViewModel extends ViewModel {
    private MutableLiveData<CurEdiGuaFormState> curEdiGuaFormState;

    public CurEdiGuaViewModel() {
        this.curEdiGuaFormState = new MutableLiveData<>();
    }

    public LiveData<CurEdiGuaFormState> getCurEdiGuaFormState() {
        return curEdiGuaFormState;
    }

    public void dataChanged(@Nullable String codigo, @Nullable String descripcion, @Nullable String creditos) {
        CurEdiGuaFormState formState = new CurEdiGuaFormState();
        boolean dataValid = true;
        if (isTextInvalid(codigo)) {
            formState.setCodigoError(R.string.invalid_username);
            dataValid = false;
        }
        if (isTextInvalid(descripcion)) {
            formState.setDescripcionError(R.string.invalid_username);
            dataValid = false;
        }
        if (isTextInvalid(creditos)) {
            formState.setCreditosError(R.string.invalid_username);
            dataValid = false;
        }
        formState.setDataValid(dataValid);
        curEdiGuaFormState.setValue(formState);
    }

    private boolean isTextInvalid(String username) {
        if (username == null)
            return true;
        return username.trim().isEmpty();
    }
}
