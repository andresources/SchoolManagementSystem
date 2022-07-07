package com.pharma.di;

import android.app.Application;

import com.pharma.LoginDIActivity;
import com.pharma.fragment.patient.StudentHomeFragment;
import com.pharma.fragment.patient.StudentProfileFragment;
import com.pharma.ui.LoginActivity;
import com.pharma.ui.SplashScreenActivity;
import com.pharma.ui.parent.ExamsDetailsActivity;
import com.pharma.ui.parent.PaymentReceiptActivity;
import com.pharma.ui.parent.StudentDashboardActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SharedPrefModule.class,UserModule.class,DatabaseModule.class})
public interface MyComponent {
    void inject(LoginDIActivity activity);
    void inject(StudentHomeFragment ac);
    void inject(StudentProfileFragment ac);
    void inject(LoginActivity activity);
    void inject(Application activity);
    void inject(ExamsDetailsActivity activity);
    void inject(PaymentReceiptActivity activity);
    void inject(SplashScreenActivity activity);
}
