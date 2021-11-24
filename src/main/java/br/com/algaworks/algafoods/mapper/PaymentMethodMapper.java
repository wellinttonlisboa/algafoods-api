package br.com.algaworks.algafoods.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.algaworks.algafoods.domain.PaymentMethod;
import br.com.algaworks.algafoods.requersts.PaymentMethodPostRequestBody;
import br.com.algaworks.algafoods.requersts.PaymentMethodPutRequestBody;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PaymentMethodMapper {

    public static final PaymentMethodMapper INSTANCE = Mappers.getMapper(PaymentMethodMapper.class);

    public abstract PaymentMethod toPaymentMethod(PaymentMethodPostRequestBody paymentMethodPostRequestBody);
    public abstract PaymentMethod toPaymentMethod(PaymentMethodPutRequestBody paymentMethodPutRequestBody);
}
