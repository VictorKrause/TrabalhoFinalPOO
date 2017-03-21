package datamanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import data.Helpfulness;
import data.Product;
import data.RankingProduto;
import data.RankingUsuarios;
import data.Review;
import data.User;

public class DataManager {
	private Map<String, Product> productsMap;
	private Map<String, User> usersMap;
	private Set<Review> reviews;

	public DataManager() {
		productsMap = new HashMap<String, Product>();
		usersMap = new HashMap<String, User>();
		reviews = new HashSet<Review>();
	}

	public void load() throws FileNotFoundException {
		// Leitura do arquivo
		Scanner reader = new Scanner(new File("Arts.txt"));
		// Variaveis iniciais
		Product product = new Product();
		User user = new User();
		Review review = new Review();
		String line;

		while (reader.hasNext()) {
			line = reader.nextLine();
			String avaliar = line.split(":")[0];
			switch (avaliar) {
			case "product/productId":
				product.setProductId(line.split(":")[1].trim());
				break;
			case "product/title":
				product.setTitle(line.split(":")[1].trim());
				break;
			case "product/price":
				product.setPrice(tryToParsePrice(line.split(":")[1].trim()));
				break;
			case "review/userId":
				user.setId(line.split(":")[1].trim());
				break;
			case "review/profileName":
				user.setProfileName(line.split(":")[1].trim());
				break;
			case "review/helpfulness":
				review.setHelpfulness(Helpfulness.parseHelpfulness(line.split(":")[1].trim()));
				break;
			case "review/score":
				review.setScore(tryToParseScore(line.split(":")[1].trim()));
				break;
			case "review/time":
				review.setTime(tryToParseTime(line.split(":")[1].trim()));
				break;
			case "review/summary":
				review.setSummary(line.split(":")[1].trim());
				break;
			case "review/text":
				review.setText(line.split(":")[1].trim());
				break;
			case "":
				// verifica se o mapa j√° contem o produto e atualiza as
				// reviews
				Set<Review> productReviews;
				if (this.productsMap.containsKey(product.getProductId())) {
					product = this.productsMap.get(product.getProductId());
					productReviews = product.getReviews();
				} else {
					productReviews = new HashSet<Review>();
				}
				productReviews.add(review);
				product.setReviews(productReviews);
				
				Set<Review> userReviews;
				if (this.usersMap.containsKey(user.getId())) {
					user = this.usersMap.get(user.getId());
					userReviews = user.getReviews();
				} else {
					userReviews = new HashSet<Review>();
				}
				userReviews.add(review);
				user.setReviews(userReviews);
				
				/**
				 * cria os vinculos: produto -> reviews / review -> usuario /  review
				 * -> produto /  usuario -> reviews;
				 **/
				review.setProduct(product);
				review.setUser(user);

				// adiciona nas estruturas de dados os valores encontrados
				this.productsMap.put(product.getProductId(), product);
				this.usersMap.put(user.getId(), user);
				this.reviews.add(review);

				// zera as variaveis
				product = new Product();
				user = new User();
				review = new Review();
			}
		}
		reader.close();
	}

	public Set<Product> getProducts() {
		return new HashSet<Product>(this.productsMap.values());
	}

	public Set<User> getUsers() {
		return new HashSet<User>(this.usersMap.values());

	}

	public Set<Review> getReviews() {
		return this.reviews;

	}

	private double tryToParsePrice(String price) {
		try {
			return new Double(price);
		} catch (NumberFormatException e) {
			// e.printStackTrace();
			return 0.0;
		}
	}
	
	private long tryToParseScore(String score) {
		try {
			return new Long(new Double(score).longValue());
		} catch (NumberFormatException e) {
			// e.printStackTrace();
			return 0L;
		}
	}
	
	public List<RankingProduto> getRankProdutos(){
		List<RankingProduto> listaProdutos = new ArrayList<RankingProduto>();
		for (Product product : getProducts()) {
			Set<Review> listreview = product.getReviews();
			double totalScoreReview = 0;
			for (Review review : listreview) {
				totalScoreReview += review.getScore();
			}
			double media = totalScoreReview/listreview.size();
			if(product.getReviews().size() > 10){
				RankingProduto ranking = new RankingProduto();
				ranking.setProduct(product);
				ranking.setMedia(media);
				listaProdutos.add(ranking);
			}
		}
		Collections.sort(listaProdutos);
		return listaProdutos.subList(0, 20);
	}
	
	public List<RankingUsuarios> getRankUsuarios(){
		Map<User, Double> usuarioResultado = new HashMap<>();
		for(User user : getUsers()) {
			double resultadoPositivos = 0;
			double resultadoTotais = 0;
			double divisao = 0;
			for(Review review: user.getReviews()){
				resultadoPositivos += review.getHelpfulness().getPositive();
				resultadoTotais += review.getHelpfulness().getTotal();
			}
			divisao = resultadoPositivos/resultadoTotais;
			usuarioResultado.put(user, divisao);
		}
		ArrayList<User> listUsuario = new ArrayList<User>(usuarioResultado.keySet());
		List<RankingUsuarios> rankingList = new ArrayList<RankingUsuarios>();
		for (User user : listUsuario) {
			RankingUsuarios rank = new RankingUsuarios();
			rank.setUser(user);
			rank.setReviewsUteis(usuarioResultado.get(user).intValue());
			rankingList.add(rank);
		}
		Collections.sort(rankingList);
		return rankingList.subList(0, 20);
	}
	
	private Map<YearMonth, Integer> organizarReviewPorData(){
		Map<YearMonth, Integer> reviewOrganizada = new HashMap();
		for(Review review: reviews){
			long time = review.getTime();
			YearMonth dataReview = unixToYearMonth(time);
			int numReviews;
			
			if(!reviewOrganizada.containsKey(dataReview))/* Se n„o tiver nenhum deste tipo cadastrado, cria a Key*/{           
                reviewOrganizada.put(dataReview, 1);
            }
            else{
                numReviews = reviewOrganizada.get(dataReview);
                numReviews++;
                reviewOrganizada.put(dataReview, numReviews);
                
               
            }
		}
		
		return reviewOrganizada;
	}

	public Map<YearMonth, Integer> selecionarDataDigitada(String dataInicial, String dataFinal){
		YearMonth dInicio = YearMonth.parse(dataInicial);
		YearMonth dFinal = YearMonth.parse(dataFinal);
		Map<YearMonth, Integer> mapCortado = new HashMap();
		Map<YearMonth, Integer> mapInicial = organizarReviewPorData();
		Set<YearMonth> keys = mapInicial.keySet();
		for(YearMonth key: keys){
			if(key.isAfter(dInicio)&&key.isBefore(dFinal)){
				mapCortado.put(key, mapInicial.get(key));				
			}
			else if(key.equals(dInicio)||key.equals(dFinal)){
				mapCortado.put(key, mapInicial.get(key));				
			}
		}
		
		return mapCortado;
	}
	
	private YearMonth unixToYearMonth(long unixTime){
		Date date = new Date(unixTime*1000L); // *1000 is to convert seconds to milliseconds
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM"); // the format of your date
		String formattedDate = sdf.format(date);
		YearMonth month = YearMonth.parse(formattedDate);
		return month;
	}
	
	private long tryToParseTime(String time) {
		try {
			return new Long(new Double(time).longValue());
		} catch (NumberFormatException e) {
			// e.printStackTrace();
			return 0L;
		}
	}
}
