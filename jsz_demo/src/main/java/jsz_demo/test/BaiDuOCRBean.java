package jsz_demo.test;

import java.util.List;

public class BaiDuOCRBean {
	private Long log_id;
    private int direction;
    private int words_result_num;
    private List<Words_result> words_result;
    //private int language;
    
    /**
	 * @return the log_id
	 */
	public Long getLog_id() {
		return log_id;
	}

	/**
	 * @param log_id 
	 * log_id
	 */
	public void setLog_id(Long log_id) {
		this.log_id = log_id;
	}

	/**
	 * @return the direction
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * @param direction 
	 * direction
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * @return the words_result_num
	 */
	public int getWords_result_num() {
		return words_result_num;
	}

	/**
	 * @param words_result_num 
	 * words_result_num
	 */
	public void setWords_result_num(int words_result_num) {
		this.words_result_num = words_result_num;
	}

	/**
	 * @return the words_result
	 */
	public List<Words_result> getWords_result() {
		return words_result;
	}

	/**
	 * @param words_result 
	 * words_result
	 */
	public void setWords_result(List<Words_result> words_result) {
		this.words_result = words_result;
	}


	public static class Words_result{
    	 private String words;
    	 private Probability probability;
    	 
    	 /**
		 * @return the words
		 */
		public String getWords() {
			return words;
		}

		/**
		 * @param words 
		 * words
		 */
		public void setWords(String words) {
			this.words = words;
		}

		/**
		 * @return the probability
		 */
		public Probability getProbability() {
			return probability;
		}

		/**
		 * @param probability 
		 * probability
		 */
		public void setProbability(Probability probability) {
			this.probability = probability;
		}

		public static class Probability{
		   private double variance;
		   private double average;
		   private double min;
		/**
		 * @return the variance
		 */
		public double getVariance() {
			return variance;
		}
		/**
		 * @param variance 
		 * variance
		 */
		public void setVariance(double variance) {
			this.variance = variance;
		}
		/**
		 * @return the average
		 */
		public double getAverage() {
			return average;
		}
		/**
		 * @param average 
		 * average
		 */
		public void setAverage(double average) {
			this.average = average;
		}
		/**
		 * @return the min
		 */
		public double getMin() {
			return min;
		}
		/**
		 * @param min 
		 * min
		 */
		public void setMin(double min) {
			this.min = min;
		}
    	}
    }
}
