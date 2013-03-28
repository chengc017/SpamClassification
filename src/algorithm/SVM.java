package algorithm;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LibSVM;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;


public class SVM {
	
	int svmnum;
	public LibSVM libsvm = null;
	double score;
	public SVM(int svmnum, Instances traindata, Instances testdata){
		try {
			
			this.svmnum = svmnum;
			this.libsvm = new LibSVM();
			this.libsvm.buildClassifier(traindata);
			
			System.out.println("Finish Building SVM");
			
			Evaluation eval = new Evaluation(testdata);
			eval.evaluateModel(libsvm, testdata);
			System.out.println(eval.toSummaryString("\nResults\n======\n",false));
//			System.out.println(eval.falseNegativeRate(0));
//			System.out.println(eval.truePositiveRate(0));
//			System.out.println(eval.trueNegativeRate(0));
//			System.out.println(eval.truePositiveRate(0));
			System.out.println("Precision:"+eval.precision(1));
			System.out.println("Recall:"+eval.recall(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
