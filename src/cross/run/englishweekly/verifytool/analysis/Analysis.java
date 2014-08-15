package cross.run.englishweekly.verifytool.analysis;

/**
 * 解析线程
 * @author gjyuan
 *
 */
public class Analysis extends Thread {

	/**
	 * 路径
	 */
	private String path;
	
	private AnalysisListener listener;
	
	public Analysis(String path,AnalysisListener listener) {
		this.path = path;
		this.listener = listener;
	}

	@Override
	public void run() {
		super.run();
		listener.onStrart();
		//xlsx解析
		XlsxAnalysis analysis = new XlsxAnalysis(path);
		if(!analysis.init()){
			listener.outError(Warning.ANALYSIS_XLSX_ERROR);
			listener.onEnd();
			return;
		}
		listener.onProgress(1, Warning.ANALYSIS_LOAD);
		
		listener.onEnd();
	}

	public interface AnalysisListener{
		public void onStrart();
		public void onProgress(int progress,String str);
		public void onEnd();
		public void outError(String error);
	}
}
