package com.example.tool;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import com.example.model.*;

public class RecommendEngine {
	
	//similarity calculator
	public static double similarity(Customer c1, Customer c2, Set<Product> c1Like, Set<Product> c1Dislike,
			Set<Product> c2Like, Set<Product> c2Dislike) {
		
		System.out.println("Comparing " + "customer[" + c1.getCustomerId() + "] with [" + c2.getCustomerId() + "]");
	
		Set<Product> term1 = new HashSet<Product>();
		Set<Product> term2 = new HashSet<Product>();
		Set<Product> term3 = new HashSet<Product>();
		Set<Product> term4 = new HashSet<Product>();
		Set<Product> term5 = new HashSet<Product>();
		
		term1.addAll(c1Like);//L1
		term1.retainAll(c2Like);//intersect L2
		
		term2.addAll(c1Dislike);//D1
		term2.retainAll(c2Dislike);//intersect D2

		term3.addAll(c1Like);//L1
		term3.retainAll(c2Dislike);//intersect D2
		
		term4.addAll(c2Like);//L2
		term4.retainAll(c1Dislike);//intersect D1
		
		term5.addAll(c1Like);
		term5.addAll(c2Like);
		term5.addAll(c1Dislike);
		term5.addAll(c2Dislike);
		
		double similarity = (double)(term1.size() + term2.size() - term3.size() - term4.size()) / (double)(term5.size());
		
		System.out.println("Similarity: " + similarity);
		
		return similarity;
	}
	
	
	//quantify the probability of a customer likes a product
	
	//make sure the product is NOT liked/disliked by the customer!!
	public static double rate(Product product, Customer customer, Set<Customer> customersLike, Set<Customer> customersDislike,
			Set<Product> c1Like, Set<Product> c1Dislike, Set<Product> c2Like, Set<Product> c2Dislike) {

		
//		customersLike.remove(customer);
//		customersDislike.remove(customer);
		
//		for(Customer c : customersLike) {
//			System.out.println("customersLike " + "product[" + product.getProductId() + "]: " + c.getCustomerId());
//		}
//		
//		for(Customer c : customersDislike) {
//			System.out.println("customersDisike " + "product[" + product.getProductId() + "]: " + c.getCustomerId());
//		}
		
		double sumOfSimilarityLike = 0;
		for(Customer c : customersLike) {
//			System.out.println("sumOfSimilarityLike before " + sumOfSimilarityLike);
			sumOfSimilarityLike += similarity(customer, c, c1Like, c1Dislike, c2Like, c2Dislike);
//			System.out.println("sumOfSimilarityLike after " + sumOfSimilarityLike);
		}
		
		double sumOfSimilarityDislike = 0;
		for(Customer c : customersDislike) {
//			System.out.println("sumOfSimilarityDislike before " + sumOfSimilarityDislike);
			sumOfSimilarityDislike += similarity(customer, c, c1Like, c1Dislike, c2Like, c2Dislike);
//			System.out.println("sumOfSimilarityDislike after " + sumOfSimilarityDislike);
		}
		
		System.out.println();
		System.out.println(sumOfSimilarityLike);
		System.out.println(sumOfSimilarityDislike);
		System.out.println(customersLike.size());
		System.out.println(customersDislike.size());
		
		return (double)(sumOfSimilarityLike - sumOfSimilarityDislike) / (double)(customersLike.size() + customersDislike.size());
	}
	
	//sort the product list according to recommendation
	public static List<Product> sort(List<Product> productList){
		return null;
	}
	
	//get top X products
	public static List<Product> recommend(int x){
		return null;
	}
	

}
