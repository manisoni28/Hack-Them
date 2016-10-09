package net.appic.hack.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.appic.hack.R;


/**
 * Created by Aradh Pillaion 07/10/15.
 */

/*
* cardStackAdapter which is going to hold list of information and setting it into CardStack
*
* */
public class CardStackAdapter extends ArrayAdapter<String> {
    Integer[] imgid={
            R.drawable.video,
            R.mipmap.hacker,
            R.mipmap.webhacking,
            R.mipmap.nethacking,
            R.mipmap.ethhacking,
            R.mipmap.mailhacking,
            R.mipmap.pashacking,
            R.mipmap.bankhacking,
            R.mipmap.comphacking,R.drawable.video,R.mipmap.whitehat,R.mipmap.blackhat,R.mipmap.greyhat,R.drawable.video,R.drawable.video,
            R.drawable.video,R.drawable.video,R.drawable.video,R.drawable.video,R.drawable.video,R.drawable.video,R.drawable.video,R.mipmap.howto,R.mipmap.recuva,R.drawable.video,R.mipmap.sqlinject,R.mipmap.denialofservice,R.drawable.video,R.mipmap.how,R.drawable.video,R.drawable.video,R.drawable.video,R.drawable.video,R.drawable.video
    };
    String arr[]={"Important Note Main Purpose of this App is not to harm any people and anyone in any way.This app focuses on how to learn hacking and become ethical hacker.For more tricks swipe this card anywhere.Thanks for liking our App","Hacking is the practice of gaining the unauthorized aceess of a system, in order to viewing, editing, copying the information or to accomplish a goal outside of the creator's original purpose. The person who is consistently engaging in hacking activities is called a hacker."+"Why do hackers hack ?\n" +
            "Just for fun\n" +
            "To show off\n" +
            "Notify many people their thoughts\n" +
            "Advantages of Hacking\n" +
            "Can be used to recover lost information where the computer password has been lost. \uF075 Teaches you that no technology is 100% secure. \uF075 To test how good security is on your own network. \uF075 They call it white hat computer hacking.\n" +
            "Website hacking\n" +
            "Network hacking\n" +
            "Ethical hacking\n" +
            "Email Hacking\n" +
            "Online banking hacking\n" +
            "Computer hacking","Website hacking\n" +
            "Hacking a website means taking control from the website owner to a person who hacks the website.","Network Hacking\n" +
            "Network Hacking is generally means gathering information about domain by using tools like Telnet, Ns look UP, Ping, Tracert, Netstat, etc… over the network.","Ethical Hacking\n" +
            "Ethical hacking is where a person hacks to find weaknesses in a system and then usually patches them..","Email Hacking\n" +
            "Email hacking is illicit access to an email account or email correspondence.","Password Hacking\n" +
            "Password Hacking Password cracking is the process of recovering secret passwords from data that has been stored in or transmitted by a computer system.","Online Banking Hacking\n" +
            "Online banking Hacking Unauthorized accessing bank accounts without knowing the password or without permission of account holder is known as Online banking hacking.","Computer Hacking\n" +
            "Computer Hacking is when files on your computer are viewed, created, or edited without your authorization.","The Types of Hackers\n" +
            "White hat hacker\n" +
            "Black hat hacker\n" +
            "Grey hat Hacker","White hat hacker\n" +
            "A white hat hacker is a computer and network expert who attacks a security system on behalf of its owners or as a hobby, seeking vulnerabilities that a malicious hacker could exploit. Instead of taking malicious advantage of exploits, a white hat hacker notifies the system's owners to fix the breach before it is can be taken advantage of.","Black hat hacker\n" +
            "A black hat is a person who compromises the security of a computer system without permission from an authorized party, typically with malicious intent. A black hat will maintain knowledge of the vulnerabilities and exploits they find for a private advantage, not revealing them to the public or the manufacturer for correction","Grey hat Hacker\n" +
            "A grey hat is a skilled hacker who sometimes will act legally and other times may not. They are a cross between white hat and black hat hackers. They usually do not hack for personal gain or have malicious intentions, but may or may not occasionally commit crimes during the course of their technological exploits.","Hack Facebook with Pishing method\n" +
            "What is Phishing??\n" +
            "Let me put it quite simple, Phishing basically means to get someone's username and passwords using a fake page which looks similar to the original one. In this example we are going to create a Fake facebook page, when eva the victim enters her username and password and logins using that fake page, the password is automatically send to us using a PHP script\n" +
            "DRAWBACK :\n" +
            "Users nowadays are aware of these type of attacks and one can not be easily fooled using this attack. You need some social engineering to trick someone.\n" +
            "Prevention :\n" +
            "Always check the page URL before logging in. This is the most trusted and effective way one can use to avoid himself from phishing. Other way is to use some good Antivirus software which will warn you if you visit a harmful phishing page. Even if somehow you have already entered your credentials in a phisher, Immediately Change your password.","Hack Facebook with Keylogging\n" +
            "What is Keylogging??\n" +
            "This is another good way of hacking Facebook accounts. In this type of attack a hacker simply sends an infected file having keylogger in it to the victim. If the victim executes that file on his pc, whatever he types will be mailed/uploaded to hacker’s server. The advantage of this attack is that the victim won’t know that hacker is getting every Bit of data he is typing. Another big advantage is that hacker will get passwords of all the accounts used on that PC.\n" +
            "DRAWBACK :\n" +
            "Keyloggers are often detected as threats by good antiviruses. Hacker must find a way to protect it from antivirus.\n" +
            "Prevention :\n" +
            "Execute the file only if you trust the sender. Use online scanner such as novirusthanks.org Use good antivirus and update it regularly","What are Trojans/backdoors??\n" +
            "This is an advanced level topic. It consists of a server and a client. In this type of attack the attacker sends the infected server to the victim. After execution the infected server i.e. Trojan on the victim’s PC opens a backdoor and now the hacker can do whatever he wants with the victim’s PC .\n" +
            "DRAWBACK :\n" +
            "Trojans are often detected as threats by good antiviruses. Hacker must find a way to protect it from antivirus.\n" +
            "Prevention :\n" +
            "Execute the file only if you trust the sender. Use online scanner such as novirusthanks.org Use good antivirus and update it regularly","What is Sniffing??\n" +
            "It consists of stealing session in progress. In this type of attack an attacker makes connection with server and client and relays message between them, making them believe that they are talking to each other directly.\n" +
            "DRAWBACK :\n" +
            "If user is logged out then attacker is also logged out and the session is lost. It is difficult to sniff on SSL protected networks..\n" +
            "Prevention :\n" +
            "Always use SSL secured connections. Always keep a look at the url if the http:// is not changed to https:// it means that sniffing is active on your network.","What is Social Engineering??\n" +
            "This method includes guessing and fooling the clients to give their own passwords. In this type of attack, a hacker sends a fake mail which is very convincing and appealing and asks the user for his password. Answering the security questions also lies under this category\n" +
            "DRAWBACK :\n" +
            "It is not easy to convince someone to make him give his password. Guessing generally doesn’t always work ( Although if you are lucky enough it may work!).\n" +
            "Prevention :\n" +
            "Never give your password to anyone Don’t believe in any sort of emails which asks for your password","What is Session Hijacking??\n" +
            "In a session hijacking attack an attacker steals victims cookies, cookies stores all the necessary logging Information about one’s account, using this info an attacker can easily hack anybody’s account. If you get the cookies of the Victim you can Hack any account the Victim is Logged into i.e. you can hack Facebook, Google, Yahoo.\n" +
            "DRAWBACK :\n" +
            "You will be logged out when user is logged out. You will not get the password of the user’s account. Will not work if the user is using HTTPS connections..\n" +
            "Prevention :\n" +
            "Always work on SSL secured connections. Always keep a look at the url if the http:// is not changed to https:// it means that sniffing is active on your network.","Android Mobile Hacks and Tricks\n" +
            "There are a lot of Android hacks out there to help you to customise your smartphone, giving you total control over its look, feel, and performance. Many android users don’t know much about the hidden features of android phones. Here are some of them , Try and enjoy !","Secret Codes For Your Android Mobile\n" +
            "Hi friends, here is a wide collection of secret codes for your mobile with Android OS(Can be Called as Android Tricks). These codes enables you to access the hidden options which are not shown by default on your device, and can be used for testing the functions of various utilities used by your mobile. Note: We Cannot guaranty that these codes will work on all Android mobiles!\n" +
            "Secrets Codes\n" +
            "Here is the list of Secret Codes \n" +
            "*#06# --Display's IMEI number\n" +
            "*#*#4636#*#* -- Display's Phone information\n" +
            "*#*#2664#*#* -- Touch screen test.\n" +
            "*#*#0*#*#* -- LCD test.\n" +
            "*#*#2663#*#* -- Touch screen version.\n" +
            "*#*#3264#*#* - RAM version.\n" +
            "*#*#232331#*#* - Bluetooth test.\n" +
            "*#*#1472365#*#* - GPS test. \n" +
            "*#*#0842#*#* - Vibration test and BackLight test.\n" +
            "*#*#1111#*#* - FTA SW Version.\n" +
            "*#12580*369# – Display's Software and hardware info.\n" +
            "*#*#2664#*#* – Testing the touchscreen\n" +
            "*#*#232339#*#* – Wireless LAN tests.","What is rooting ?\n" +
            "When you root android phone or tablet and other android devices it means that you get administrative privileges and can edit or delete system files. And a new world of functionality and device customization is now open for you.\n" +
            "Why you root?\n" +
            "Now we tell you some benefits of rooting. If you need these benefits only then root android phone or tablet otherwise no need to do this. Here they are. \n" +
            "\n" +
            "You can remove manufacturer installed or carrier installed applications which you are not going to use ever. Allow you to run applications like firewall and many other that is impossible on unrooted phone. Customize phone look according to your wish and installing themes. You can flash custom ROMs which help in increasing you phone performance and also you can update to latest android version which is not yet released for your handset. Rooting allows wireless tethering.\n" +
            "Drawbacks of rooting ?\n" +
            "It will void your device warranty. But you can unroot your device to get it back. Your android device is no more immune to security threats. Risk of bricking your device during rooting. But its chances are zero percent if you follow proper instructions.\n" +
            "How to root android phone\n" +
            "Here are steps\n" +
            "Download the framaroot app from google in your PC and transfer it in your phone using datacable\n" +
            "Or directly download in your android phone or tablet.\n" +
            "Now install it in your android device by running APK file you just downloaded.\n" +
            "Now run this application and select option “install superuser”..\n" +
            "After this a list of exploits appears, select one of them and wait until it complete its work.\n" +
            "If it shows “success” message then you rooted your android device successfully.\n" +
            "So now restart your device for the changes to take effect.\n" +
            "If it shows “failed” message then try other exploits in the list.","Find Android Phone Even On Silent Mode\n" +
            "I think we all agree that it becomes very difficult to find your phone when you’ve lost your phone somewhere around. Most of us start making calls although it doesn’t make any sense if vibration mode too is off. Here I am going to tell you a better way to find your android device if it is lost somewhere around. "+"Steps\n" +
            "Here are steps\n" +
            "Go to Android Device Manager by Google in your desktop browser.\n" +
            "follow the link (www.google.com/android/devicemanager)\n" +
            "Login with the same Gmail account you used to activate your Android Device before.\n" +
            "Now you will see your device, and last login date, three options i.e. Ring, Lock and Erase.\n" +
            "By clicking on Ring, your device starts ringing at high volume. .\n" +
            "Which help you find your phone. This function works even when your phone is in silent mode.\n" +
            "You can also Lock your device with a password in case device is lost.\n" +
            "You can input a msg and a number to make sure whoever finds your phone, can call you back.\n" +
            "If the Location services of the device are turned on, you can find the exact location of ","How To Recover Lost Data In Android\n" +
            "If You are not rooted !\n" +
            "Step1:\n" +
            "The first thing to do is to connect the phone as a Mass Storage Device to your computer, ie, via Data Cable.\n" +
            "Step2:\n" +
            "Then download \"Recuva\" (you can use either the paid or free version), which is a program to recover files."+"Step3:\n" +
            "When you run Recuva, the first thing to do is select the type of file you want to recover: Pictures, Videos, Music, Documents, etc. \n" +
            "Important: When you finish recovering your deleted files try to save them on a different device from the one on which they were originally lost: this can sometimes cause conflicts and damage recovery.\n" +
            "If You are root !\n" +
            "Step1:\n" +
            "Download \"Undelete\" app on your android.\n" +
            "Step2:\n" +
            "The application is very simple. The first thing to do is to select the storage device from which you want to retrieve your data. \n" +
            "Step3:\n" +
            "Then you scan the selected device.\n" +
            "Step4:\n" +
            "The process may take between 2 and 10 minutes, depending on your memory size and the amount of stored data.\n" +
            "Finally: When finished you will be presented with retrievable data in several tabs: Files, Pictures, Music, Videos, Documents and Files. Recovered data returns to its place of origin, ie wherever it was saved before they were lost.","10 Android Tips and Tricks\n" +
            "Here is an article with secret android tricks and hidden features of android phones. Different versions of android versions support different set of android tricks, so try them all\n" +
            "1) Force reboot:\n" +
            "Many times android users face a problem of freezing in mobile phone, in case your android phone is frozen, you can reboot it: Just press Power Button+ Home Key + Volume up button simultaneously.\n" +
            "2) Bypass Applock in android:\n" +
            "1. Take the target phone in which you want to hack AppLock. 2. Now go to settings and then tap on Apps or Applications. 3. You will see AppLock app under downloaded section and tap on it. 4. After that tap on Force stop option. With this AppLock application is currently in idle state. You can open locked apps with out entering password.\n" +
            "3) Quick Google Access:\n" +
            "Android users don’t know that android phones provide a way by which we can access Google search in just a single click: Press menu key and hold it for couple of seconds, it will launch the Google search\n" +
            "4) Reboot Android in safe mode:\n" +
            "To reboot your android in safe mode, follow the below given instructions. *Long press the power button *Long press on the power off option. Android phone will show a confirmation message about rebooting it in safe mode.\n" +
            "6) Move android apps to SD card:\n" +
            "Its good idea to install android apps in SD card, but what if you installed apps on your phone memory. Don’t worry! Android phones provide a way by which we can move our apps to SD card. To move apps to SD card, follow these steps: Go to settings > Application settings > Manage application > Select the application, You will see the option “Move to SD card”.\n" +
            "7)Hard Reset your android phone:\n" +
            "To hard reset a phone dial *2767*3855#, this will delete all the data as well as settings of android phone. Don’t try this code for testing purpose, until you are not sure. It will not ask for any confirmation.\n" +
            "8) Context menu in android:\n" +
            "Long press on the screen, will show you additional options for customizing android phones. This context menu is somewhat similar to the right click menu of most operating systems.\n" +
            "9) Taking screen shots on android phone\n" +
            "Android phones offer a great feature by which we can take the screen shot without using any 3rd party application. However the screen shot android tricks vary from one android version to another version. Press the Home button + power button. For Galaxy Nexus: Power button + volume down button. For Galaxy Note 2 and S3: Swipe your palm on the screen to take screen shot.\n" +
            "10) Android Version Animation:\n" +
            "Go to settings > about phone > Tab repeatedly on ‘Android version’. After sometime, the Android version will be animated","The Simple SQL Injection Hack\n" +
            "SQL Injection involves entering SQL code into web forms, eg. login fields, or into the browser address field, to access and manipulate the database behind the site, system or application. When you enter text in the Username and Password fields of a login screen, the data you input is typically inserted into an SQL command. This command checks the data you’ve entered against the relevant table in the database. If your input matches table/row data, you’re granted access (in the case of a login screen). If not, you’re knocked back out."+"In its simplest form, this is how the SQL Injection works. It’s impossible to explain this without reverting to code for just a moment. Don’t worry, it will all be over soon. \n" +
            "Suppose we enter the following string in a User name field: \n" +
            "‘ OR 1=1 — \n" +
            "The authorization SQL query that is run by the server, the command which must be satisfied to allow access, will be something along the lines of: \n" +
            "\n" +
            "SELECT * FROM users WHERE username = ‘USRTEXT ‘ AND password = ‘PASSTEXT’\n" +
            "\n" +
            "…where USRTEXT and PASSTEXT are what the user enters in the login fields of the web form.\n" +
            "So entering `OR 1=1 — as your username, could result in the following actually being run: \n" +
            "\n" +
            "SELECT * FROM users WHERE username = ‘‘ OR 1=1 — ‘AND password = ‘’ \n" +
            "Two things you need to know about this:\n" +
            "[‘] closes the [user-name] text field. \n" +
            "‘--‘ \n" +
            "is the SQL convention for Commenting code, and everything after Comment is ignored. So the actual routine now becomes:\n" +
            "SELECT * FROM users WHERE user name = ” OR 1=1 \n" +
            "1 is always equal to 1, last time I checked. So the authorization routine is now validated, and we are ushered in the front door to wreck havoc.\n" +
            "Another Example\n" +
            "An Injection Attack could have this command line:\n" +
            "\n" +
            "String query = “SELECT * FROM accounts WHERE custID='” + request.getParameter(“id”) +”‘”; \n" +
            "The hacker modifies the ‘id’ parameter in their browser to send: ‘ or ‘1’=’1 . This changes the meaning of the query to return all the records from the accounts database to the hacker, instead of only the intended customers.","Denial of service ( Ddos attack )\n" +
            "A denial of service attack (DOS) is an attack through which a person can render a system unusable or significantly slow down the system for legitimate users by overloading the resources, so that no one can access it"
            +"This is not actually hacking a webite but it is used to take down a website. If an attacker is unable to gain access to a machine, the attacker most probably will just crash the machine to accomplish a denial of service attack,this one of the most used method for website hacking.\n" +
            "Prevention\n" +
            "Buy More Bandwidth.\n" +
            "Restricted Connectivity.\n" +
            "Opt for DDoS Mitigation Services","Cross site scripting ( XSS )\n" +
            "A cross-site scripting attack is a kind of attack on web applications in which attackers try to inject malicious scripts to perform malicious actions on trusted websites. In cross-site scripting, malicious code executes on the browser side and affects users. Cross-site scripting is also known as an XSS attack. The first question that comes in mind is why we call it “XSS” instead of “CSS.” The answer is simple and known to all who work in web development. In web design, we have cascading style sheet s (CSS). So cross-site scripting is called XSS so it does not get confused with CSS.\n" +
            "Impact of XSS\n" +
            "When attackers successfully exploit XSS vulnerabilities in a web application, they can insert script that gives them access to end users' account credentials. Attackers can perform a variety of malicious activities, such as:\n" +
            "Hijack an account\n" +
            "Spread web worms\n" +
            "Access browser history and clipboard contents\n" +
            "Control the browser remotely\n" +
            "Scan and exploit intranet appliances and applications\n" +
            "Prevention\n" +
            "To help prevent XSS attacks, an application needs to ensure that all variable output in a page is encoded before being returned to the end user. Encoding variable output substitutes HTML markup with alternate representations called entities. The browser displays the entities but does not run them,\n" +
            "To ensure that malicious scripting code is not output as part of a page, your application needs to encode all variable strings before they're displayed on a page.","How to become a Hacker\n" +
            "Hacking is an engaging field but it is surely not easy. To become a hacker one has to have an attitude and curiosity of learning and adapting new skills. You must have a deep knowledge of computer systems, programming languages, operating systems and the journey of learning goes on and on."+"Some people think that a hacker is always a criminal and do illegal activities but they are wrong. Actually many big companies hire hackers to protect their systems and information and are highly paid. here is the list of most important steps necessary to become a hacker, have a deeper look\n" +
            "Steps:\n" +
            "Learn UNIX/LINUX\n" +
            "NUNIX/LINUX is an open source operating system which provides better security to computer systems. It was first developed by AT&T in Bell labs and contributed a lot in the world of security. You should install LINUX freely available open source versions on your desktops as without learning UNIX/LINUX, it is not possible to become a hacker. ."+"Code in C language\n" +
            "C programming is the base of learning UNIX/LINUX as this operating system is coded in C programming which makes it the most powerful language as compared to other programming languages. C language was developed by Dennis Ritchie in late 1970’s. To become a hacker you should master C language."+"Code in more than one Programming Language\n" +
            "It is important for a person in the hacking field to learn more than one programming. There are many programming languages to learn such as Python, JAVA, C++. Free eBooks, tutorials are easily available online."+"Learn Networking Concepts\n" +
            "Another important and essential step to become a hacker is to be good at networking concepts and understanding how the networks are created. You need to know the differences between different types of networks and must have a clear understanding of TCP/IP and UDP to exploit vulnerabilities (loop holes) in system. Understanding what LAN, WAN, VPN, Firewall is also important. You must have a clear understanding and use of network tools such as Wireshark, NMAP for packet analyzing, network scanning etc."+"Learn More Than One Operating Systems\n" +
            "It is essential for a hacker to learn more than one operating system. There are many other Operating systems apart from Windows, UNIX/LINUX etc. Every system has a loop hole, hacker needs it to exploit it.","Please Do check the News And Chat Sections For Current Updates and also Like Our APP and rate it also.\nThank You.\nWe will be soon with you all with new tips and tricks.",""







    };

    public CardStackAdapter(Context context, int resource) {

        super(context, 0);
    }

    @Override
    public View getView(int position, final View contentView, ViewGroup parent) {
        //supply the layout for your card
        TextView v = (TextView) (contentView.findViewById(R.id.helloText));
        ImageView i=(ImageView)(contentView.findViewById(R.id.ima));
        v.setText(arr[position]);
        i.setImageResource(imgid[position]);
        return contentView;
    }

}
