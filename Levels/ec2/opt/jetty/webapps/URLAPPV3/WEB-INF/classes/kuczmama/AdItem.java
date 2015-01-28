/**
 * Mark Kuczmarski
 * URLAPP
 * CSE 383
 * File: AdItem.java
 * Description:  An Ad object in order to display the ads to the user
 */

package kuczmama;

import java.util.*;
import org.springframework.stereotype.Component;

public class AdItem {
    static String[] Names = new String[]{"id","text","link","image"};
    String[] values;


    public AdItem() {
	values = new String[4];
    }

    public void set(String k, String v) {
	for (int i=0;i<Names.length;i++) {
	    if (k.equals(Names[i])) {
		values[i] = v;
	    }
	}
    }

    public String getId() {
	return values[0];
    }

    public String getText() {
	return values[1];
    }

    public String getLink() {
	return values[2];
    }
    
    public String getImage() {
	return values[3];
    }


    public String toString() {
	return "Id=" + values[0] + " Text="+values[1] + " Link=" + values[2] + " image=" + values[3];
    }
}


/*
package kuczmama;

import java.util.*;
import org.springframework.stereotype.Component;


public class AdItem {
    private String id, text, link, image;

    public AdItem(String id, String text, String link, String image){
	this.id = id;
	this.text = text;
	this.link = link;
	this.image = image;
    }

    public String getId(){
	return this.id;
    }

    public String getText(){
	return this.text;
    }

    public String getLink(){
	return this.link;
    }

    public String getImage(){
	return this.image;
    }

    public String toString(){
	return id + " " + text + " " + link + " " + image;
    }
}
*/
