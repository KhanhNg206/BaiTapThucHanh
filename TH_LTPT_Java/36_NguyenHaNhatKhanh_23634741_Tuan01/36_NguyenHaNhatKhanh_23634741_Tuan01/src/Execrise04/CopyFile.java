package Execrise04;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
		
		tfFrom.setText("T:\\BTTH_ptjv.pdf");
		tfTo.setText("T:\\BTTH_ptjv_copy.pdf");
		
		btnCopy.addActionListener(e -> {
		    File from = new File(tfFrom.getText());
		    File to = new File(tfTo.getText());

		    if (!from.exists()) {
		        JOptionPane.showMessageDialog(this, "File nguồn không tồn tại!");
		        return;
		    }

		    progress.setValue(0);
		    CopyTask task = new CopyTask(from, to);

		    task.addPropertyChangeListener(evt -> {
		        if ("progress".equals(evt.getPropertyName())) {
		            progress.setValue((Integer) evt.getNewValue());
		        }
		    });

		    task.execute();
		});

	}
	
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeLater(() -> new CopyFile().setVisible(true));
	}
	
	class CopyTask extends SwingWorker<Long, Integer> {

	    private File fileFrom;
	    private File fileTo;

	    public CopyTask(File fileFrom, File fileTo) {
	        this.fileFrom = fileFrom;
	        this.fileTo = fileTo;
	    }

	    @Override
	    protected Long doInBackground() throws Exception {
	        long totalSize = Files.size(fileFrom.toPath());
	        long copied = 0;

	        try (
	            BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileFrom));
	            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileTo))
	        ) {
	            byte[] buffer = new byte[1024];
	            int bytesRead;

	            while ((bytesRead = in.read(buffer)) != -1) {
	                out.write(buffer, 0, bytesRead);
	                copied += bytesRead;

	                int percent = (int) (copied * 100 / totalSize);
	                setProgress(percent);
	            }
	        }
	        return totalSize;
	    }

	    @Override
	    protected void done() {
	        try {
	            long size = get();
	            JOptionPane.showMessageDialog(null,
	                    "Copy hoàn tất!\nDung lượng: " + size + " bytes");
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, "Lỗi khi copy file!");
	            e.printStackTrace();
	        }
	    }
	}


}
