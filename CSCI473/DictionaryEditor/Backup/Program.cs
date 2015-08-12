/**
 * Assignemt Part: 3
 * Program.cs
 *
 * Description: This program implements a GUI for a dictionary editor program,
 *  it links to SpellChecker.dll to implement the background work of the dictionary.
 *  See DictionaryForm.cs for the most recent changes. 
 *
 * Name: Nicholas Nagrodski
 * ZID: Z140294
 * Course: CSCI473 - .NET
 * Prof: Zerwekh
 * Due Date: 2011/03/07
 * Version: 3.0.0.0
 *
 */
using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;
using System.Diagnostics;
using System.Threading;
namespace DictionaryEditor
{
  static class Program
  {
    /// <summary>
    /// The main entry point for the application.
    /// </summary>
    [STAThread]
    static void Main()
    {
      Application.EnableVisualStyles();
      Application.SetCompatibleTextRenderingDefault(false);

      // Add event handling code to catch an unrecoverable error.
      Application.ThreadException += new ThreadExceptionEventHandler(Application_ThreadException);
      Application.Run(new DictionaryForm());
    }


    // Controls error handing for a ThreadException.
    // The code prompts the user if they want to restart the
    //  program and writes the error info to a log file.
    public static void Application_ThreadException(object sender, ThreadExceptionEventArgs teea)
    {
      // Fatal error. Show the user what went wrong.
      // Ask them if they want to restart the program.
      DialogResult result = MessageBox.Show("There has been a fatal error with the program:\n"
        + "\nError Message: " + teea.Exception.Message
        + "\nStack Trace: " + teea.Exception.StackTrace
        + "\n\nWould you like to restart the program?",
          "Error",
          MessageBoxButtons.OKCancel,
          MessageBoxIcon.Error);

      // Write the exception's information to a log file.
      // It is written to the directory of the main executable.
      // Also, dumps are appended to the current file.
      StreamWriter sw = new StreamWriter("Errorlog.txt", true);
      sw.WriteLine("FATAL ERROR DUMP");
      sw.WriteLine("TIME: " + DateTime.Now.ToString()); // Write date and time to file.
      sw.WriteLine("Error Message: " + teea.Exception.Message); // Write message exception.
      sw.WriteLine("Data: " + teea.Exception.Data);
      sw.WriteLine("Stack Trace:\n" + teea.Exception.StackTrace);
      sw.WriteLine("END ERROR MESSAGE\n");
      sw.Close();

      // If the user pressed OK, get the directory location of this program's
      //  executable and start a new instance of it.
      if (result.Equals(DialogResult.OK))
      {
        Process newProcess = new Process();
        Process current = Process.GetCurrentProcess();

        newProcess.StartInfo.FileName = current.MainModule.FileName;
        newProcess.Start();
        current.Kill(); // Kill the current Process.
      }
    }  // End public static void Application_ThreadException
  } // End class Program
} // End namespace DictionaryEditor
