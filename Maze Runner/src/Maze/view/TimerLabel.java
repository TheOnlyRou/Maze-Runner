/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.view;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 *
 * @author user
 */
public class TimerLabel extends JLabel implements ActionListener
{
  /** Timer that updates time. */
  private Timer _timer;

  /** Used to format the displayed date. */
  private DateFormat _fmt = DateFormat.getTimeInstance(DateFormat.LONG);
  private Dimension _prefSize;
  private Calendar _calendar = Calendar.getInstance();


  public TimerLabel()
  {
    super("", JLabel.CENTER);
  }

  /**
   * Add component to its parent. Start the timer for auto-update.
   */
  public void addNotify()
  {
    super.addNotify();
    _timer = new Timer(1000, this);
    _timer.start();
  }

  /**
   * Remove component from its parent. Stop the timer.
   */
  public void removeNotify()
  {
    super.removeNotify();
    if (_timer != null)
    {
      _timer.stop();
      _timer = null;
    }
  }

  /**
   * Update component with the current time.
   *
   * @param evt   The current event.
   */
  public void actionPerformed(ActionEvent evt)
  {
    _calendar.setTimeInMillis(System.currentTimeMillis());
    setText(_fmt.format(_calendar.getTime()));
  }

  /**
   * Return the preferred size of this component.
   *
   * @return  the preferred size of this component.
   */
  public Dimension getPreferredSize()
  {
    if(null == _prefSize)
    {
      // This was originaly done every time.
      // and the count of instantiated objects was amazing
      _prefSize = new Dimension();
      _prefSize.height = 20;
      FontMetrics fm = getFontMetrics(getFont());
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.HOUR_OF_DAY, 23);
      cal.set(Calendar.MINUTE, 59);
      cal.set(Calendar.SECOND, 59);
      _prefSize.width = fm.stringWidth(_fmt.format(cal.getTime()));
      Border border = getBorder();
      if (border != null)
      {
        Insets ins = border.getBorderInsets(this);
        if (ins != null)
        {
          _prefSize.width += (ins.left + ins.right);
        }
      }
      Insets ins = getInsets();
      if (ins != null)
      {
        _prefSize.width += (ins.left + ins.right) + 20;
      }
    }
    return _prefSize;
  }
    
}
