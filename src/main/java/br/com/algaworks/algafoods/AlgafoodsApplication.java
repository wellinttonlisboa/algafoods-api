package br.com.algaworks.algafoods;

import java.util.List;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import br.com.algaworks.algafoods.controller.KitchenController;
import br.com.algaworks.algafoods.domain.Kitchen;
import br.com.algaworks.algafoods.requersts.KitchenPostRequestBody;
import br.com.algaworks.algafoods.requersts.KitchenPutRequestBody;

public class AlgafoodsApplication {

    public static void main(String[] args) {
        ApplicationContext appContext = new SpringApplicationBuilder(AlgafoodsApiApplication.class).web(WebApplicationType.NONE).run(args);

        KitchenController kitchenController = appContext.getBean(KitchenController.class);

        Kitchen kitchen1 = new Kitchen();
        kitchen1.setName("Brasileira");

        KitchenPostRequestBody kitchenPost1 = KitchenPostRequestBody.builder().name(kitchen1.getName()).build();

        kitchenController.save(kitchenPost1);

        ResponseEntity<List<Kitchen>> kitchens1 = kitchenController.listAll();
        for (Kitchen kitchen : kitchens1.getBody()) {
            System.out.println(kitchen.getId());
            System.out.println(kitchen.getName());
        }

        ResponseEntity<Kitchen> kitchen2 = kitchenController.findById(2);
        System.out.println(kitchen2.getBody().getName());

        kitchen2.getBody().setName("Tailandesa (UPDATED)");

        KitchenPutRequestBody kitchenPut1 = KitchenPutRequestBody.builder().id(kitchen2.getBody().getId())
                .name(kitchen2.getBody().getName()).build();

        kitchenController.replace(kitchenPut1);

        ResponseEntity<List<Kitchen>> kitchens2 = kitchenController.listAll();
        for (Kitchen kitchen : kitchens2.getBody()) {
            System.out.println(kitchen.getId());
            System.out.println(kitchen.getName());
        }

        kitchenController.delete(1);

        ResponseEntity<List<Kitchen>> kitchens3 = kitchenController.listAll();
        for (Kitchen kitchen : kitchens3.getBody()) {
            System.out.println(kitchen.getId());
            System.out.println(kitchen.getName());
        }
    }

}
