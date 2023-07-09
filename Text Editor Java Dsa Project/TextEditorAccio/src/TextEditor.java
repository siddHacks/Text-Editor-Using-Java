import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //declaring properties of textEditor
    //for the desktop frame
    JFrame frame;

    //for MenuBar
    JMenuBar menuBar;

    //for menus
    JMenu file,edit,mode;

    //file menu items
    JMenuItem newFile,openFile,saveFile;

    //edit menu items
    JMenuItem cut,copy,paste,selectAll,close,bold,italic;

    //mode menu items
    JMenuItem Default,Green,Cyan,Yellow,Black;

    //for textArea
    JTextArea textArea;

    TextEditor(){
        //Initialize a frame
        frame = new JFrame();
        //Initialize a MenuBar
        menuBar = new JMenuBar();
        //Initialize a textArea
        textArea = new JTextArea();
        textArea.setBackground(Color.white);

        //Initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");
        mode = new JMenu("Mode");


        //Initialize menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //Add action listeners to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //Add menus items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        bold = new JMenuItem("Bold");
        italic = new JMenuItem("Italic");

        //Add action listeners to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        bold.addActionListener(this);
        italic.addActionListener(this);

        //add menu items to edit menu
         edit.add(cut);
         edit.add(copy);
         edit.add(paste);
         edit.add(selectAll);
         edit.add(close);
         edit.add(bold);
         edit.add(italic);

         //Initialize Mode
          Default = new JMenuItem("Default");
          Green = new JMenuItem("Green");
          Cyan = new JMenuItem("Cyan");
          Yellow = new JMenuItem("Yellow");
          Black = new JMenuItem("Black");

          //Add action listener to mode menu items
        Default.addActionListener(this);
        Green.addActionListener(this);
        Cyan.addActionListener(this);
        Yellow.addActionListener(this);
        Black.addActionListener(this);

        //Add  menu items to Mode
        mode.add(Default);
        mode.add(Green);
        mode.add(Cyan);
        mode.add(Yellow);
        mode.add(Black);

        //Add menu to menuBar
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(mode);

        //set menuBar to frame
        frame.setJMenuBar(menuBar);
         //create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //Add text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        //create Scroll pane
        JScrollPane scrollPane =new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to panel
        panel.add(scrollPane);
        //Add panel to frame
        frame.add(panel);
        //Set Dimensions of frame
        frame.setBounds(100,100,800,800); //x & y  telling ->position of the frame
        frame.setTitle("Text Editor");
        //frame visibility
        frame.setVisible(true);
        //Layout of frame
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
       if(actionEvent.getSource() == cut){
          textArea.cut();
       }
       if(actionEvent.getSource() == copy){
           textArea.copy();
       }
       if(actionEvent.getSource() == paste){
           textArea.paste();
       }
       if(actionEvent.getSource() == selectAll){
           textArea.selectAll();
       }
       if(actionEvent.getSource() == close){
           //perform close editor operation
           System.exit(0);
       }
       if(actionEvent.getSource() == bold){
           Font font = new Font("Arial",Font.BOLD,14);
           textArea.setFont(font);
       }
        if(actionEvent.getSource() == italic){
            Font font = new Font("Arial",Font.ITALIC,14);
            textArea.setFont(font);
        }
       if(actionEvent.getSource() == openFile) {
           JFileChooser fileChooser = new JFileChooser("D:");
           int chooseOption = fileChooser.showOpenDialog(null);

           //if its open button
           if (chooseOption == JFileChooser.APPROVE_OPTION) {
               //Getting selected file
               File file = fileChooser.getSelectedFile();
               //get the path of selected file
               String filepath = file.getPath();
               try {
                   //Initialized file reader
                   FileReader fileReader = new FileReader(filepath);
                   //Initialized buffer reader
                   BufferedReader bufferedReader = new BufferedReader(fileReader);
                   String intermediate = "", output = "";
                   //Read contents of file line by  line

                   while ((intermediate = bufferedReader.readLine()) != null) {
                       output += intermediate + "\n";
                   }
                   //Set the output String to  textArea
                   textArea.setText(output);

               } catch (FileNotFoundException fileNotFoundException) {
                   fileNotFoundException.printStackTrace();
               } catch (IOException ioException) {
                   ioException.printStackTrace();
               }
           }
       }
           if(actionEvent.getSource() == saveFile){
               //Initialize file picker
               JFileChooser fileChooser1 = new JFileChooser("D:");
               //Get choose option from file chooser
               int chooseOption1 = fileChooser1.showSaveDialog(null);
               //check if we clicked on save button
               if(chooseOption1 == JFileChooser.APPROVE_OPTION){
                   //create new file with chosen directory path and name
                   File file1 = new File(fileChooser1.getSelectedFile().getAbsolutePath()+".txt");
                   try{
                       //Initialize file writer
                       FileWriter fileWriter = new FileWriter(file1);
                       //Initialize Buffered writer
                       BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                       //Write contents of text area to file
                       textArea.write(bufferedWriter);
                       bufferedWriter.close();
                   }catch(IOException ioException){
                       ioException.printStackTrace();
                   }
               }

           }
           if(actionEvent.getSource() == newFile){
               TextEditor newTextEditor = new TextEditor();
           }
           if(actionEvent.getSource() == Default){
               textArea.setBackground(Color.white);
               textArea.setForeground(Color.BLACK);
           }
        if(actionEvent.getSource() == Green){
            textArea.setBackground(Color.GREEN);
            textArea.setForeground(Color.BLACK);
        }
        if(actionEvent.getSource() == Cyan){
            textArea.setBackground(Color.CYAN);
            textArea.setForeground(Color.BLACK);
        }
        if(actionEvent.getSource() == Yellow){
            textArea.setBackground(Color.YELLOW);
            textArea.setForeground(Color.BLACK);
        }
        if(actionEvent.getSource() == Black){
            textArea.setBackground(Color.BLACK);
            textArea.setForeground(Color.WHITE);
        }

    }
    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();
    }
}