package com.xpeppers.linkingcommerce.merchantdashboard.android;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.xpeppers.linkingcommerce.merchantdashboard.R;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderDetailsView;
import com.xpeppers.linkingcommerce.merchantdashboard.models.order.OrderStatus;

public class AndroidOrderDetailsView implements OrderDetailsView {

    private final Activity activity;
    private TextView emailField;
    private TextView titleField;
    private TextView purchaseDateField;
    private TextView couponCodeField;
    private TextView orderStatusField;
    private OrderStatusHelper orderStatusHelper;
    private final AndroidMessageAlert alert;

    public AndroidOrderDetailsView(Context context, TextView emailField, TextView titleField, TextView purchaseDateField, TextView couponCodeField, TextView orderStatusField) {
        this.activity = (Activity) context;
        this.emailField = emailField;
        this.titleField = titleField;
        this.purchaseDateField = purchaseDateField;
        this.couponCodeField = couponCodeField;
        this.orderStatusField = orderStatusField;
        this.orderStatusHelper = new OrderStatusHelper(context);
        this.alert = new AndroidMessageAlert(activity);
    }

    @Override
    public void showBuyerEmail(String email) {
        emailField.setText(email);
    }

    @Override
    public void showTitle(String title) {
        titleField.setText(title);
    }

    @Override
    public void showPurchaseDate(String purchaseDate) {
        purchaseDateField.setText(purchaseDate);
    }

    @Override
    public void showCouponCode(String code) {
        couponCodeField.setText(code);
    }

    @Override
    public void showOrderStatus(OrderStatus orderStatus) {
        orderStatusField.setText(orderStatusHelper.getOrderStatus(orderStatus));
    }

    @Override
    public void showError() {
        alert.show(activity.getString(R.string.generic_error), activity.getString(R.string.generic_error_message));
    }
}
