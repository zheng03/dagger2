package com.vertical.app.module.transaction;

import com.vertical.annotation.AutoWire;
import com.vertical.annotation.autolayout.AutoLayout;
import com.vertical.app.R;
import com.vertical.app.base.BaseCatActivity;
import com.vertical.app.bean.OrderBean;

import java.sql.Date;
import java.sql.Timestamp;

import butterknife.OnClick;

/**
 * Created by ls on 10/12/17.
 */

@AutoWire(presenter = CreateOrderPresenter.class)
@AutoLayout(layout = R.layout.activity_createorder, title = "销售开单")
public class CreateOrderActivity extends BaseCatActivity<CreateOrderContract.Presenter> implements CreateOrderContract.View {

    @Override
    public void onOrderCreation(boolean success) {
    }

    @OnClick(R.id.cancel)
    protected void cancel() {
        finish();
    }

    @OnClick(R.id.submit)
    protected void submit() {
        OrderBean orderBean = new OrderBean();
        orderBean.amount = 1.1;
        orderBean.comments = "第一个商品";
        orderBean.create_by = 20171001;
        Timestamp timestamp = new Timestamp(2017, 10, 10, 12, 11, 1, 123);
        orderBean.create_date = timestamp.toString();
        Date date = new Date(2017, 2, 30);
        orderBean.order_date = date.toString();
        orderBean.id = 656;
        orderBean.order_id = 776;
        orderBean.quantity = 1;
        orderBean.product_name = "手表";
        orderBean.status = 0;
        orderBean.order_picture_id = 11;

        long receiver_id = 1;
        long goods_id = 1;
        int payment = 1;
        int goods_count = 1;
        double order_bill = 100.00;
        String comments = "nice try";


        mPresenter.createOrder(receiver_id, goods_id, payment, goods_count, order_bill, comments);
    }
}
