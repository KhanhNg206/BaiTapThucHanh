package exercise4;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class CopyFile extends JFrame{
	
	private JTextField tfFrom;
	private JTextField tfTo;
	private JButton btnCopy;
	private JProgressBar progress;

	public CopyFile() {
		setTitle("Copy File");
		setSize(400, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Box b, b1, b2, b3 ,b4;
		add(b = Box.createVerticalBox());
		
		b.add(b1 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10));
		b.add(b2 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10));
		b.add(b3 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10));
		b.add(b4 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10));
		
		JLabel lblFrom, lblTo;
		b1.add(lblFrom = new JLabel("From: ")); b1.add(tfFrom = new JTextField());
		b2.add(lblTo = new JLabel("To: ")); b2.add(tfTo = new JTextField());
		
		b3.add(btnCopy = new JButton("Copy...."));
		b4.add(progress = new JProgressBar());
		progress.setStringPainted(true);
		
		b.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		lblTo.setPreferredSize(lblFrom.getPreferredSize());
		
		tfFrom.setText("C:\\Users\\Student\\Documents\\Ubuntu-VMWARE.rar");
		tfTo.setText("C:\\Users\\Student\\Documents\\Ubuntu-VMWARE2.rar");
		
		btnCopy.addActionListener((e) -> {
			File fromFile = new File(tfFrom.getText().trim());
			File toFile = new File(tfTo.getText().trim());
			
			CopyTask task = new CopyTask(fromFile, toFile);
			task.execute();
			task.addPropertyChangeListener(evt -> {
				if("progress".equals(evt.getPropertyName()))
					progress.setValue((int) evt.getNewValue());
			});
		});
	}
	
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeLater(() -> new CopyFile().setVisible(true));
	}
}
//1. Byte Stream
//in: InputStream
//out: OutputStream
//2. Character Stream
//in: Reader
//out: Writer
class CopyTask extends SwingWorker<Long, Long>{
	private File fromFile;
	private File toFile;
	
	public CopyTask(File fromFile, File toFile) {
		super();
		this.fromFile = fromFile;
		this.toFile = toFile;
	}

	@Override
	protected Long doInBackground() throws Exception {
		
		long size = Files.size(Path.of(fromFile.getAbsolutePath()));
		long n = 0L;
		
		try(
				FileInputStream in = new FileInputStream(fromFile);
				BufferedInputStream bIn = new BufferedInputStream(in);
 				
				FileOutputStream out = new FileOutputStream(toFile);
				BufferedOutputStream bOut = new BufferedOutputStream(out);
				){
					byte[] buff = new byte[1024];
					while(bIn.available() > 0) {
						int x = bIn.read(buff);
						bOut.write(buff, 0, x);
						
						publish(n);
						
						n += x;
						setProgress((int) (n * 100 / size));
					}
		}
		
		return size;
	}
	
	@Override
	protected void done() {
		try {
			Long size = get();
			JOptionPane.showMessageDialog(null, "Completed, size: " + size);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void process(List<Long> chunks) {
		System.out.println(chunks);
	}
	
}
