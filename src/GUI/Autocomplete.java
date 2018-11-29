package GUI;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class Autocomplete implements DocumentListener 
{
public static List<String> keywords=new ArrayList<String>();

  private static enum Mode 
  {
    INSERT,
    COMPLETION
  };

  private JTextField textField;
  
  private Mode mode = Mode.INSERT;

  public Autocomplete(JTextField textField)
  {
    this.textField = textField;
    
    
    Collections.sort(keywords);
    System.out.println("in other "+keywords.size());
  }

  @Override
  public void changedUpdate(DocumentEvent ev) { }

  @Override
  public void removeUpdate(DocumentEvent ev) { }

  @Override
  public void insertUpdate(DocumentEvent ev) {
    if (ev.getLength() != 1)
      return;

    int pos = ev.getOffset();
    String content = null;
    try {
      content = (textField.getText(0, pos + 1)).toLowerCase();
    } catch (BadLocationException e) {
      e.printStackTrace();
    }

    // Find where the word starts
    int w;
    for (w = pos; w >= 0; w--) {
      if (!Character.isLetter(content.charAt(w))) {
        break;
      }
    }

    // Too few chars
    if (pos - w < 2)
      return;

    String prefix = content.substring(w + 1).toLowerCase();
    int n = Collections.binarySearch(keywords, prefix);
    
    if (n < 0 && -n <= keywords.size()) {
      String match = keywords.get(-n - 1);
      if (match.startsWith(prefix)) {
        // A completion is found
        String completion = match.substring(pos - w);
        // We cannot modify Document from within notification,
        // so we submit a task that does the change later
        SwingUtilities.invokeLater(new CompletionTask(completion, pos + 1));
      }
    } else {
      // Nothing found
      mode = Mode.INSERT;
    }
  }

  public class CommitAction extends AbstractAction {
    /**
     * 
     */
    private static final long serialVersionUID = 5794543109646743416L;

    @Override
    public void actionPerformed(ActionEvent ev) {
      if (mode == Mode.COMPLETION) {
        int pos = textField.getSelectionEnd();
        StringBuffer sb = new StringBuffer((textField.getText()).toLowerCase());
        sb.insert(pos, " ");
        textField.setText(sb.toString().toUpperCase());
        textField.setCaretPosition(pos + 1);
        mode = Mode.INSERT;
      } else {
        textField.replaceSelection("\t");
        System.out.println("no match");
      }
    }
  }

  private class CompletionTask implements Runnable {
    private String completion;
    private int position;

    CompletionTask(String completion, int position) {
      this.completion = completion;
      this.position = position;
    }

    public void run() {
      StringBuffer sb = new StringBuffer(textField.getText().toLowerCase());
      sb.insert(position, completion);
      textField.setText(sb.toString().toUpperCase());
      textField.setCaretPosition(position + completion.length());
      textField.moveCaretPosition(position);
      mode = Mode.COMPLETION;
    }
  }
  
  
  
  
	public static ArrayList<String> getCompanyNames()
	{
		ArrayList<String> names = new ArrayList<String>();
		try
		{
          FileReader reader = new FileReader("src/Resources/configurations/company.config");
          BufferedReader bufferedReader = new BufferedReader(reader);
          String line;
         
          while ((line = bufferedReader.readLine()) != null)
          {
              names.add(line);
          }
          reader.close();

      } catch (IOException e) 
      {
          e.printStackTrace();
      }
		return names;
	}
	
	public static void addCompanyName(String name)
	{
		try 
		{
          FileWriter writer = new FileWriter("src/Resources/configurations/company.config", true);
          BufferedWriter bufferedWriter = new BufferedWriter(writer);
          
          bufferedWriter.write(name.toLowerCase());
          bufferedWriter.newLine();
          bufferedWriter.close();
          
      } catch (IOException e) {
          e.printStackTrace();
      }
	}
	
	public static boolean isCompanyExists(String name)
	{
		ArrayList<String> company_names=getCompanyNames();
		name=name.toLowerCase();
		int flag=0;
		String tmp = name.replaceAll("\\s","");
		System.out.println(tmp);
		
		for(int i=0;i<company_names.size();i++)
		{
			if(tmp.equals(company_names.get(i).replaceAll("\\s","")))
			{
				flag++;
			}
		}
		
		if(flag==0)
		{
			return false;
		}
		else
		{
			return true;
		}
		
	}

}