package rflint2740ex3i;

import javax.swing.DefaultListModel;

public class DriverExam {
	private char[] answers;
	private char[] responses;
	private final double requiredPct = 0.7;
			
public DriverExam(char[] answers) {
	this.answers = new char[answers.length];
	for (int i = 0; i < answers.length; i++){	
	}
}
//Constructor logic is nearly identical to setResponses()
public DriverExam(DefaultListModel<?> answers) {
	this.answers = new char[answers.getSize()];
	for(int i = 0; i < answers.getSize(); i++){
	String a = (String) answers.get(i);
	this.answers[i] = a.charAt(0); 
	}
}

/*setResponses(): copy elements from Strings in
 * DefaultListModel to char[]
 */
public void setResponses(DefaultListModel<?> responses) {
	this.responses = new char[responses.getSize()];
	for ( int i = 0; i < responses.getSize(); i++){
		String r = (String) responses.get(i);
		this.responses[i] = r.charAt(0);
	}
}
@SuppressWarnings("unused")
public DefaultListModel<Object> getAnswers() {
	DefaultListModel<Object> answersListModel = new DefaultListModel<Object>();
	this.answers = new char[answers.length];
	for ( int i = 0; i < answers.length; i++)
	return answersListModel;
	return answersListModel;
	
}
/*validate(): return index of first element found in
 * responses[] that is not A. B. C. or D
 */
public int validate() {
	int invalidIndex = -1;
	int i = 0;
	while (i < answers.length && invalidIndex == -1 ) {
		if (responses[i] != 'A' &&
				responses[i] != 'B' &&
				responses[i] != 'C' &&
				responses[i] != 'D')
			invalidIndex = i;
		else i++;
	}
	return invalidIndex;
	}

/* passed(); call totalCorrect(). Return 'true' if
 * totalCorrect() >= requiredPct times the number of questions 
 */
public boolean passed() {
	return false;
}
/* totalCorrect(): Use loop to process all elements of
 * answers[] and responses[]. Count the number of correct
 *  answers, where answers [i] == responses[i]
 */
public int totalCorrect() {
	return 0;
}	
/* totalIncorrect(): same as totalCorrect(), but counts elements
 * where answers[i] != responses[i]
 */
public int totalIncorrect() {
	return 0;
}
/* questionsMissed(): return int[] containing question numbers
 * int[] missed = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
 * int m = 0;
 * for loop ...
 * {
 * 		if answer is incorrect
 *		{
 *			missed[m] = i +1;
 *			m++;
 *		}
 *	}
 * return missed;
 */
public int[] questionsMissed() {
	int[] missed = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	int m = 0;
	
	{
		if(m == 1)
		
		{
			missed[m] = m + 1;
					m++;
		}
	}
	return missed;
}
public double getRequiredPct() {
	return requiredPct;
}
}
