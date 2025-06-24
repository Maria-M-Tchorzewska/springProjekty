package com.example.BookStoreFinalProject.payments;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${stripe.api.secret.key}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public Map<String, Object> createPaymentIntent(PaymentRequest paymentRequest) throws StripeException {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(paymentRequest.getAmount().multiply(new java.math.BigDecimal(100)).longValue())
                .setCurrency(paymentRequest.getCurrency())
                .setDescription(paymentRequest.getDescription())
                .build();

        PaymentIntent intent = PaymentIntent.create(params);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("clientSecret", intent.getClientSecret());
        return responseData;
    }
}
