package br.com.algaworks.algafoods;

import java.util.List;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.web.method.ControllerAdviceBean;

public class AlgafoodsApplication {

//    public static void main(String[] args) {
//        ApplicationContext appContext = new SpringApplicationBuilder(AlgafoodsApiApplication.class).web(WebApplicationType.NONE).run(args);
//
//        KitchenController kitchenController = appContext.getBean(KitchenController.class);
//
//        Kitchen kitchen1 = new Kitchen();
//        kitchen1.setName("Brasileira");
//
//        KitchenPostRequestBody kitchenPost1 = KitchenPostRequestBody.builder().name(kitchen1.getName()).build();
//
//        kitchenController.save(kitchenPost1);
//
//        ResponseEntity<List<Kitchen>> kitchens1 = kitchenController.listAll();
//        for (Kitchen kitchen : kitchens1.getBody()) {
//            System.out.println(kitchen.getId());
//            System.out.println(kitchen.getName());
//        }
//
//        ResponseEntity<Kitchen> kitchen2 = kitchenController.findById(2);
//        System.out.println(kitchen2.getBody().getName());
//
//        kitchen2.getBody().setName("Tailandesa (UPDATED)");
//
//        KitchenPutRequestBody kitchenPut1 = KitchenPutRequestBody.builder().id(kitchen2.getBody().getId())
//                .name(kitchen2.getBody().getName()).build();
//
//        kitchenController.replace(kitchenPut1);
//
//        ResponseEntity<List<Kitchen>> kitchens2 = kitchenController.listAll();
//        for (Kitchen kitchen : kitchens2.getBody()) {
//            System.out.println(kitchen.getId());
//            System.out.println(kitchen.getName());
//        }
//
//        kitchenController.delete(1);
//
//        ResponseEntity<List<Kitchen>> kitchens3 = kitchenController.listAll();
//        for (Kitchen kitchen : kitchens3.getBody()) {
//            System.out.println(kitchen.getId());
//            System.out.println(kitchen.getName());
//        }
//    }
	public static void main(String[] args) {
		ApplicationContext appContext = new SpringApplicationBuilder(AlgafoodsApiApplication.class)
				.run(args);

		//System.out.println(ControllerAdviceBean.findAnnotatedBeans(appContext));
		
		//RestaurantController restaurantController = appContext.getBean(RestaurantController.class);

		System.out.println(ControllerAdviceBean.findAnnotatedBeans(appContext));
		List<ControllerAdviceBean> beans = ControllerAdviceBean.findAnnotatedBeans(appContext);
	    AnnotationAwareOrderComparator.sort(beans);
		
		System.out.println(ControllerAdviceBean.findAnnotatedBeans(appContext));
		
	    
	    
//		Restaurant restaurant1 = new Restaurant();
//		restaurant1.setName("Sushi Bahia");
//		//restaurant1.setFreight(5d);
//
//		RestaurantPostRequestBody restaurantPost1 = RestaurantPostRequestBody.builder().name(restaurant1.getName())
//				.freight(restaurant1.getFreight()).build();

//		restaurantController.save(restaurantPost1);

//		ResponseEntity<List<Restaurant>> restaurants1 = restaurantController.listAll();
//		for (Restaurant restaurant : restaurants1.getBody()) {
//			System.out.println(restaurant.getId());
//			System.out.println(restaurant.getName());
//			System.out.println(restaurant.getFreight());
//			System.out.println(restaurant.getKitchen().getName());
//		}

//		ResponseEntity<Restaurant> restaurant2 = restaurantController.findById(2);
//		System.out.println(restaurant2.getBody().getName());
//
//		restaurant2.getBody().setName("Madri (UPDATED)");
//
//		RestaurantPutRequestBody restaurantPut1 = RestaurantPutRequestBody.builder().id(restaurant2.getBody().getId())
//				.name(restaurant2.getBody().getName()).freight(restaurant2.getBody().getFreight()).build();
//
//		restaurantController.replace(restaurantPut1);
//
//		ResponseEntity<List<Restaurant>> restaurants2 = restaurantController.listAll();
//		for (Restaurant restaurant : restaurants2.getBody()) {
//			System.out.println(restaurant.getId());
//			System.out.println(restaurant.getName());
//			System.out.println(restaurant.getFreight());
//		}
//
//		restaurantController.delete(1);
//
//		ResponseEntity<List<Restaurant>> restaurants3 = restaurantController.listAll();
//		for (Restaurant restaurant : restaurants3.getBody()) {
//			System.out.println(restaurant.getId());
//			System.out.println(restaurant.getName());
//			System.out.println(restaurant.getFreight());
//		}
	}
	
	
//	private void initControllerAdviceCaches(ApplicationContext applicationContext) {
//	    if (applicationContext == null) {
//	        return;
//	    }
//	    if (logger.isInfoEnabled()) {
//	        logger.info("Looking for @ControllerAdvice: " + applicationContext);
//	    }
//	    List<ControllerAdviceBean> beans = ControllerAdviceBean.findAnnotatedBeans(applicationContext);
//	    AnnotationAwareOrderComparator.sort(beans);
//	    for (ControllerAdviceBean bean : beans) {
//	        Class<?> beanType = bean.getBeanType();
//	        Set<Method> attrMethods = selectMethods(beanType, ATTRIBUTE_METHODS);
//	        if (!attrMethods.isEmpty()) {
//	            this.modelAttributeAdviceCache.put(bean, attrMethods);
//	            if (logger.isInfoEnabled()) {
//	                logger.info("Detected @ModelAttribute methods in " + bean);
//	            }
//	        }
//	        Set<Method> binderMethods = selectMethods(beanType, BINDER_METHODS);
//	        if (!binderMethods.isEmpty()) {
//	            this.initBinderAdviceCache.put(bean, binderMethods);
//	            if (logger.isInfoEnabled()) {
//	                logger.info("Detected @InitBinder methods in " + bean);
//	            }
//	        }
//	        ExceptionHandlerMethodResolver resolver = new ExceptionHandlerMethodResolver(beanType);
//	        if (resolver.hasExceptionMappings()) {
//	            this.exceptionHandlerAdviceCache.put(bean, resolver);
//	            if (logger.isInfoEnabled()) {
//	                logger.info("Detected @ExceptionHandler methods in " + bean);
//	            }
//	        }
//	    }
//	}
	
}
