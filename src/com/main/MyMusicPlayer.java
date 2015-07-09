package com.main;
import java.awt.*;
import javax.swing.*;
import java.applet.*;
import java.awt.event.*;
import java.net.*;

public class MyMusicPlayer extends Thread implements MouseListener,ItemListener {
	JFrame MainFrame=new JFrame("MyMusicPlayer");      //定义主窗体
	JLabel songname=new JLabel();                    //用标签显示状态
	JButton last=new JButton();
	JButton play=new JButton();                
	JButton next=new JButton();
	JButton loop=new JButton();                       //定义上一曲,播放,下一曲,循环四个按钮
	JLabel list=new JLabel("播放列表");
	List songlist=new List();                         //显示歌曲列表
	AudioClip[] song=new AudioClip[10];                     //将音频文件放在数组中
	AudioClip playsong;                                     //当前选定播放的音频文件
	String[] name={"黄小琥 - 顺其自然.wav","陈奕迅-张氏情歌.wav","黄小琥 _没那么简单.wav",
			 "戚薇-如果爱忘了.wav","郭采洁 - 狠狠哭.wav","test.wav"};    
	        //将所有文件名存放在字符串数组name中
	String playname;                         //当前选定播放的音频名
	int j=0;                                //记录当前选定播放的是哪首歌曲，默认为第一首
	boolean playbutton=true;                     //记录播放状态，默认为暂停播放
	boolean loopbutton=false;                     //记录循环状态，默认为没有循环
    Thread thread=new Thread("pl");
    static MyMusicPlayer Yu;
    
    public  MyMusicPlayer(){
    	 MainFrame.setLayout(null);
    	 MainFrame.setBounds(300,50,310,500);              
    	 MainFrame.setVisible(true);
    	 MainFrame.setDefaultCloseOperation(3);
    	 
    	 MainFrame.add(songname);
    	 Font sname=new Font("斜体",Font.ITALIC,18);     
    	 songname.setFont(sname);                          //设置显示状态的字体为斜体
    	 songname.setText("我的音乐播放器");               
    	 songname.setBounds(10,10,300,40);
   
    	 last.setBounds(10,70,50,40);
    	 play.setBounds(70,70,50,40);                   //设置四个功能键位置和大小
    	 next.setBounds(130,70,50,40);
    	 loop.setBounds(190,70,50,40);
    	 last.setIcon(new ImageIcon("1.png"));            
    	 play.setIcon(new ImageIcon("2.png"));
    	 next.setIcon(new ImageIcon("3.png"));
    	 loop.setIcon(new ImageIcon("4.png"));            //设置四个功能键的图片
    	 last.addMouseListener(this);
    	 play.addMouseListener(this);
    	 next.addMouseListener(this);                 
    	 loop.addMouseListener(this);                     //添加按键鼠标监听器
    	 
    	 MainFrame.add(last);
    	 MainFrame.add(play);
    	 MainFrame.add(next);
    	 MainFrame.add(loop);                       
    	
      	list.setBounds(10,120,100,30);
      	Font lis=new Font("宋",Font.BOLD,15);
      	list.setFont(lis);                         //显示“播放列表”
      	MainFrame.add(list);
      	
        songlist.setBounds(10,150,250,300);           
        songlist.setBackground(Color.GREEN);           //设置播放列表的背景色为绿色
      	songlist.setVisible(true);
      	songlist.addItemListener((ItemListener) this);          //添加列表监听器
      	MainFrame.add(songlist);
      	
      	for(int i=0;i<name.length;i++)	                              
      	{
      		 song[i]=loadSound(name[i]);          //逐个获取音频文件
      		 songlist.add(name[i]);                       //将歌曲名添加到播放列表	
      	}  
      	playsong=song[0];
      	
     }
    
     public static void main(String[] args){
    	 Yu=new MyMusicPlayer();
      	 Yu.start();
     }
     
	public void mouseClicked(MouseEvent e) {
		JButton btn=(JButton)e.getSource();
		playsong.stop();
		if(btn==play)
		{   if(playbutton==false)
		        playbutton=true;                    
		    else
		        playbutton=false;                  //当按下play后改变播放状态
		}
		else
			if(btn==last)
			 {   j-=1;                              //当按下last后将上一曲选定
			     if(j<0)
				     j=name.length-1;               //若之前选定为第一首，则跳到最后一首
				 playbutton=true;
				 loopbutton=false;
			 }
			else
				if(btn==next)
				{   j+=1;                          //当按下next后将下一曲选定
				    if(j>=name.length)
				    	j=0;                       //若之前选定为最后一首，则跳到第一首
					playbutton=true;
					loopbutton=false;
				}
				else
					if(btn==loop)
					{  if(loopbutton==false) 
					    {  
					        loopbutton=true;
					        playbutton=true;
					    }
					   else
					    {                               //按下loop后，改变循环状态和播放状态
					        loopbutton=false;
					        playbutton=false;
					    }
					}  
		
		if(playbutton==true)
			Yu.run();
		else
		{	                            
		    songname.setText("暂停播放："+playname);    //暂停播放歌曲  
		    play.setIcon(new ImageIcon("2.png"));
		}
	}
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void itemStateChanged(ItemEvent arg0) {
		String str=songlist.getSelectedItem();
		playsong.stop();
		for(int i=0;i<name.length;i++)
		   if(str==name[i])
		     { 
		       j=i;
		       break;
		     }
		Yu.run();
	   
	}
	public void run(){
		playsong=song[j];              //播放状态为播放时
	    playsong.play();                //播放选定歌曲
        playname=name[j];             
        if(loopbutton==true)
        {                               
        	  playsong.loop();            //循环播放选定歌曲
        	  songname.setText("循环播放："+playname);
        }
        else
        	songname.setText("正在播放："+playname);
        
        songlist.select(j);                       //播放列表中选定播放歌曲项目
        play.setIcon(new ImageIcon("5.png"));	   
		
	}
	
	public AudioClip loadSound(String filename){        //返回一个AudioClip对象
	   URL url=null;                                                                   
	   try{
	        url=new URL("file:"+filename);                                
	      }catch(MalformedURLException e){ }
	   return Applet.newAudioClip(url);                           
	}

}
