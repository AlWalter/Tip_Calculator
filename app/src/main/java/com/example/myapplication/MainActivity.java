package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etBillAmount)
    TextView etBillAmount;
    @BindView(R.id.tvTipPercent)
    TextView tvTipPercent;
    @BindView(R.id.tvTipTotal)
    TextView tvTipTotal;
    @BindView(R.id.tvBillTotalAmount)
    TextView tvBillTotalAmount;
    private float percentage = 0;
    private float tipTotal = 0;
    private float finalBillAmount = 0;
    private float totalBillAmount = 0;

    private float REGULAR_PERCENTAGE = 5;
    private float DEFAULT_PERCENTAGE = 10;
    private float EXCELLENT_PERCENTAGE = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState!=null) {
            percentage = savedInstanceState.getFloat("percentage");
        }
        setTipValues();
    }

    private void setTipValues() {
        tvTipPercent.setText(getString(R.string.main_msg_tippersent, percentage));
        tvTipTotal.setText(getString(R.string.main_msg_tiptotal, tipTotal));
        tvBillTotalAmount.setText(getString(R.string.main_msg_billtotalresult, finalBillAmount));
    }

    @OnClick({R.id.ibRegularService, R.id.ibGoodService, R.id.ibExcellentService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibRegularService:
                percentage = REGULAR_PERCENTAGE;
                break;
            case R.id.ibGoodService:
                percentage = DEFAULT_PERCENTAGE;
                break;
            case R.id.ibExcellentService:
                percentage = EXCELLENT_PERCENTAGE;
                break;
        }
        calculateFinalBill();
        setTipValues();
    }

    @OnTextChanged(R.id.etBillAmount)
    public void onTextChange() {
        calculateFinalBill();
        setTipValues();
    }

    private void calculateFinalBill() {
        if (percentage == 0) {
            percentage = DEFAULT_PERCENTAGE;
        }
        if (!etBillAmount.getText().toString().equals("") && !etBillAmount.getText().toString().equals(".")) {
            totalBillAmount = Float.valueOf(etBillAmount.getText().toString());
        } else {
            totalBillAmount = 0;
        }
        tipTotal = totalBillAmount * percentage / 100;
        finalBillAmount = totalBillAmount + tipTotal;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putFloat("percentage", percentage);
        super.onSaveInstanceState(outState);
    }
}
