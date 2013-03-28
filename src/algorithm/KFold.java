package algorithm;

import java.util.Vector;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class KFold {

	/**
	 * @param args
	 */
	public static void KCrossValidation(String dataPath, int k){
		try {
			//import
			DataSource source = new DataSource(dataPath);
			Instances data = source.getDataSet();
			
			if (data.classIndex() == -1) {
				data.setClassIndex(data.numAttributes() - 1);
			}
			//split
			Vector<Instances> folds = new Vector<Instances>();
			for(int i=0;i<k;i++){
				DataSource kSource = new DataSource(dataPath);
				Instances kData = source.getDataSet();
				if (kData.classIndex() == -1) {
					kData.setClassIndex(kData.numAttributes() - 1);
				}
				kData.clear();
				folds.add(kData);
			}
			for(int i=0;i<data.numInstances();i++){
				folds.get(i%k).add(data.instance(i));
			}
			//classify
			DataSource trainSource = new DataSource(dataPath);
			Instances trainData = source.getDataSet();
			if (trainData.classIndex() == -1) {
				trainData.setClassIndex(trainData.numAttributes() - 1);
			}
			DataSource testSource = new DataSource(dataPath);
			Instances testData = source.getDataSet();
			if (testData.classIndex() == -1) {
				testData.setClassIndex(testData.numAttributes() - 1);
			}
			
			for(int i=0;i<k;i++){
				trainData.clear();
				testData.clear();
				for(int j=0;j<k;j++){
					if(j==i){
						//test
						testData.addAll(folds.get(j));
					}
					else{
						trainData.addAll(folds.get(j));
					}
				}
				//
				System.out.println("=====\r\nBegin Training No."+i+" fold");
				SVM svm = new SVM(0,trainData,testData);
			}
//			for(int i=0;i<k;i++){
//				System.out.println(folds.get(i).numInstances());
//			}
//			System.out.println(trainData.numInstances());
//			System.out.println(testData.numInstances());
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KCrossValidation("data/tf.arff", 5);
	}

}
