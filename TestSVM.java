package mysystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;
import libsvm.svm_parameter;
import libsvm.svm_problem;

public class TestSVM {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:\\�����ʼ��ʵ�.txt"))));
		String dataline = null;
		double[] Trainlables = new double[10000];
		svm_node[][] Trainline = new svm_node[10000][50];
		double[] Testlables = new double[1000];
		svm_node[][] Testline = new svm_node[1000][50];
		int j = 0;
		int k = 0;
		/*
		 * public int l; public double[] y; public svm_node[][] x;
		 */
		while ((dataline = br.readLine()) != null) {
			String[] seg = dataline.split("\t");
			if (seg.length != 6) {
				continue;
			}
			String pk_id = seg[0];
			String createdate = seg[1];
			String level = seg[2];
			String status = seg[3];
			String hasimgfile = seg[4];
			String SentenceVec = seg[5];
// ���������Կո����
			String[] dataseg = SentenceVec.split(" ");
			svm_node[] linenode = new svm_node[50];
// ѵ������
			if (j < 10000) {
				for (int i = 1; i < dataseg.length; i++) {
					svm_node node = new svm_node();
					node.index = i;
					node.value = Double.parseDouble(dataseg[i]);
					;
					linenode[i - 1] = node;
				}
				Trainline[j] = linenode;
				Trainlables[j] = Double.parseDouble(status);
				j = j + 1;
			}
// ��������
			else if ((j > 9999 && j < 11000) && k < 1000) {
				for (int i = 1; i < dataseg.length; i++) {
					svm_node node = new svm_node();
					node.index = i;
					node.value = Double.parseDouble(dataseg[i]);
					;
					linenode[i - 1] = node;
				}
				Testline[k] = linenode;
				Testlables[k] = Double.parseDouble(status);
				j = j + 1;
				k = k + 1;
			}
			System.out.println(j);

		}

		br.close();
// ����svm_problem����
		svm_problem problem = new svm_problem();
		problem.l = 10000; // ��������
		problem.x = Trainline; // ѵ����������
		problem.y = Trainlables; // ��Ӧ��lable����

// ����svm_parameter����
		svm_parameter param = new svm_parameter();
		param.svm_type = svm_parameter.C_SVC;
		param.kernel_type = svm_parameter.LINEAR;
		param.cache_size = 100;
		param.eps = 0.00001;
		param.C = 2;
 
// ѵ��SVM����ģ��
// �������û�����⣬��svm.svm_check_parameter()��������null,���򷵻�error������
		System.out.println(svm.svm_check_parameter(problem, param));
// svm.svm_train()ѵ����SVM����ģ��
		svm_model model = svm.svm_train(problem, param);
//����ģ��
		svm.svm_save_model("D:\\a.txt" + "SVMModel", model);

// ��������
		double Normal2Normal = 0;
		double Normal2Sapm = 0;
		double Sapm2Sapm = 0;
		double Spam2Normal = 0;
		for (int k1 = 0; k1 < 1000; k1++) {
			System.out.println(k1);
			System.out.println(Testline[k1]);
			double pred_result = svm.svm_predict(model, Testline[k1]);
			if ((Testlables[k1] == 1) && (pred_result == 1)) {
				Normal2Normal += 1;
			}
			if ((Testlables[k1] == 1) && (pred_result == 2)) {
				Normal2Sapm += 1;
			}
			if ((Testlables[k1] == 2) && (pred_result == 2)) {
				Sapm2Sapm += 1;
			}
			if ((Testlables[k1] == 2) && (pred_result == 1)) {
				Spam2Normal += 1;
			}
			System.out.println(pred_result);
		}
		System.out.println(Normal2Normal + "\t" + Normal2Sapm + "\t" + Sapm2Sapm + "\t" + Spam2Normal);
	}

}
