package com.infy.ekart.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;



import com.infy.ekart.dto.CartProductDTO;
import com.infy.ekart.dto.CustomerCartDTO;
import com.infy.ekart.dto.ProductDTO;
import com.infy.ekart.entity.CartProduct;
import com.infy.ekart.entity.CustomerCart;
import com.infy.ekart.exception.EKartException;
import com.infy.ekart.repository.CartProductRepository;
import com.infy.ekart.repository.CustomerCartRepository;

//Add the missing annotation
public class CustomerCartServiceImpl implements CustomerCartService {

	private CustomerCartRepository customerCartRepository;

	private CartProductRepository cartProductRepository;

	// this method adds new product to cart
	@Override
	public Integer addProductToCart(CustomerCartDTO customerCartDTO) throws EKartException {
		Set<CartProduct> cartProducts = new HashSet<>();
		Integer cartId = null;
		for (CartProductDTO cartProductDTO : customerCartDTO.getCartProducts()) {
			CartProduct cartProduct = new CartProduct();
			cartProduct.setProductId(cartProductDTO.getProduct().getProductId());
			cartProduct.setQuantity(cartProductDTO.getQuantity());
			cartProducts.add(cartProduct);
		}
		Optional<CustomerCart> cartOptional = customerCartRepository
				.findByCustomerEmailId(customerCartDTO.getCustomerEmailId());
		if (cartOptional.isEmpty()) {
			CustomerCart newCart = new CustomerCart();
			newCart.setCustomerEmailId(customerCartDTO.getCustomerEmailId());
			newCart.setCartProducts(cartProducts);
			customerCartRepository.save(newCart);
			cartId = newCart.getCartId();
		} else {
			CustomerCart cart = cartOptional.get();
			for (CartProduct cartProductToBeAdded : cartProducts) {
				Boolean found = false;
				for (CartProduct cartProductFromCart : cart.getCartProducts()) {
					if (cartProductFromCart.equals(cartProductToBeAdded)) {

						cartProductFromCart
								.setQuantity(cartProductToBeAdded.getQuantity() + cartProductFromCart.getQuantity());
						found = true;
					}
				}
				if (found == false) {
					cart.getCartProducts().add(cartProductToBeAdded);
				}
			}

			cartId = cart.getCartId();
		}
		return cartId;
	}

	// this method retrieves customer data from repository and returns cart details
	// of that customer
	@Override
	public Set<CartProductDTO> getProductsFromCart(String customerEmailId) throws EKartException {
		Optional<CustomerCart> cartOptional = customerCartRepository.findByCustomerEmailId(customerEmailId);
		Set<CartProductDTO> cartProductsDTO = new HashSet<>();
		CustomerCart cart = cartOptional.orElseThrow(() -> new EKartException("CustomerCartService.NO_CART_FOUND"));

		if (cart.getCartProducts().isEmpty()) {
			throw new EKartException("CustomerCartService.NO_PRODUCT_ADDED_TO_CART");
		}
		Set<CartProduct> cartProducts = cartOptional.get().getCartProducts();
		for (CartProduct cartProduct : cartProducts) {
			CartProductDTO cartProductDTO = new CartProductDTO();
			cartProductDTO.setCartProductId(cartProduct.getCartProductId());
			cartProductDTO.setQuantity(cartProduct.getQuantity());
			ProductDTO productDTO = new ProductDTO();
			productDTO.setProductId(cartProduct.getProductId());
			cartProductDTO.setProduct(productDTO);
			cartProductsDTO.add(cartProductDTO);
		}
		return cartProductsDTO;
	}

	@Override
	public void deleteProductFromCart(String customerEmailId, Integer productId) throws EKartException {

		Optional<CustomerCart> cartOptional = customerCartRepository.findByCustomerEmailId(customerEmailId);
		CustomerCart cart = cartOptional.orElseThrow(() -> new EKartException("CustomerCartService.NO_CART_FOUND"));
		if (cart.getCartProducts().isEmpty()) {
			throw new EKartException("CustomerCartService.NO_PRODUCT_ADDED_TO_CART");
		}
		CartProduct selectedProduct = null;
		for (CartProduct product : cart.getCartProducts()) {
			if (product.getProductId().equals(productId)) {
				selectedProduct = product;

			}
		}
		if (selectedProduct == null) {
			throw new EKartException("CustomerCartService.PRODUCT_ALREADY_NOT_AVAILABLE");
		}

		cart.getCartProducts().remove(selectedProduct);
		cartProductRepository.delete(selectedProduct);
	}

	@Override
	public void deleteAllProductsFromCart(String customerEmailId) throws EKartException {
		Optional<CustomerCart> cartOptional = customerCartRepository.findByCustomerEmailId(customerEmailId);
		CustomerCart cart = cartOptional.orElseThrow(() -> new EKartException("CustomerCartService.NO_CART_FOUND"));

		if (cart.getCartProducts().isEmpty()) {
			throw new EKartException("CustomerCartService.NO_PRODUCT_ADDED_TO_CART");
		}
		List<Integer> productIds = new ArrayList<>();
		cart.getCartProducts().parallelStream().forEach(cp -> {
			productIds.add(cp.getCartProductId());
			cart.getCartProducts().remove(cp);
		});

		productIds.forEach(pid -> {

			cartProductRepository.deleteById(pid);
		});

	}

	@Override
	public void modifyQuantityOfProductInCart(String customerEmailId, Integer productId, Integer quantity)
			throws EKartException {

		Optional<CustomerCart> cartOptional = customerCartRepository.findByCustomerEmailId(customerEmailId);
		CustomerCart cart = cartOptional.orElseThrow(() -> new EKartException("CustomerCartService.NO_CART_FOUND"));

		if (cart.getCartProducts().isEmpty()) {
			throw new EKartException("CustomerCartService.NO_PRODUCT_ADDED_TO_CART");
		}
		CartProduct selectedProduct = null;
		for (CartProduct product : cart.getCartProducts()) {
			if (product.getProductId().equals(productId)) {
				selectedProduct = product;
			}
		}
		if (selectedProduct == null) {
			throw new EKartException("CustomerCartService.PRODUCT_ALREADY_NOT_AVAILABLE");
		}
		selectedProduct.setQuantity(quantity);
	}

}