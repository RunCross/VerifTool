package cross.run.englishweekly.verifytool.bean;

/**
 * 每期的基本信息
 * @author gjyuan
 *
 */
public class BaseRegex {

	public String code;
	public String publish;
	public String area;
	public String year;
	public String grade;
	public String issues;
	public String type;
	public String name;
	public String time;
	
	
	public BaseRegex(String code, String publish, String area, String year,
			String grade, String issues, String type, String name) {
		super();
		this.code = code;
		this.publish = publish;
		this.area = area;
		this.year = year;
		this.grade = grade;
		this.issues = issues;
		this.type = type;
		this.name = name;
	}
	
	
}
