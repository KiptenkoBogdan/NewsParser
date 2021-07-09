package ua.edu.sumdu.j2ee.kiptenko.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;

@ToString
@XmlRootElement
@Setter
@Getter @NoArgsConstructor
public class Pojo {
    private String title;
    private String description;
    private String url;
    private String author;
    private String urlToImage;
//    private String category;
//    private String country;
//    private String language;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title == null){
            this.title = " - ";
        } else{
            this.title = title;
        }
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public void setDescription(String description) {
        if(description == null){
            this.description = " - ";
        } else{
            this.description = description;
        }
    }

    public void setUrl(String url) {
        if(url == null){
            this.url = " - ";
        } else{
            this.url = url;
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if(author == null){
            this.author = " - ";
        } else{
            this.author = author;
        }
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        if(urlToImage == null){
            this.urlToImage = " - ";
        } else{
            this.urlToImage = urlToImage;
        }
    }

//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getLanguage() {
//        return language;
//    }
//
//    public void setLanguage(String language) {
//        this.language = language;
//    }
}
