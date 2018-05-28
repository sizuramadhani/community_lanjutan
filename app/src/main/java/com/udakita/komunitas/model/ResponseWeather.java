package com.udakita.komunitas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by leonardo.siagian on 6/5/2017.
 */

public class ResponseWeather {

    public class Units
    {
        @SerializedName("distance")
        @Expose
        private String distance;

        public String getDistance() { return this.distance; }

        public void setDistance(String distance) { this.distance = distance; }

        @SerializedName("pressure")
        @Expose
        private String pressure;

        public String getPressure() { return this.pressure; }

        public void setPressure(String pressure) { this.pressure = pressure; }

        @SerializedName("speed")
        @Expose
        private String speed;

        public String getSpeed() { return this.speed; }

        public void setSpeed(String speed) { this.speed = speed; }

        @SerializedName("temperature")
        @Expose
        private String temperature;

        public String getTemperature() { return this.temperature; }

        public void setTemperature(String temperature) { this.temperature = temperature; }
    }

    public class Location
    {
        @SerializedName("city")
        @Expose
        private String city;

        public String getCity() { return this.city; }

        public void setCity(String city) { this.city = city; }

        @SerializedName("country")
        @Expose
        private String country;

        public String getCountry() { return this.country; }

        public void setCountry(String country) { this.country = country; }

        @SerializedName("region")
        @Expose
        private String region;

        public String getRegion() { return this.region; }

        public void setRegion(String region) { this.region = region; }
    }

    public class Wind
    {
        @SerializedName("chill")
        @Expose
        private String chill;

        public String getChill() { return this.chill; }

        public void setChill(String chill) { this.chill = chill; }

        @SerializedName("direction")
        @Expose
        private String direction;

        public String getDirection() { return this.direction; }

        public void setDirection(String direction) { this.direction = direction; }

        @SerializedName("speed")
        @Expose
        private String speed;

        public String getSpeed() { return this.speed; }

        public void setSpeed(String speed) { this.speed = speed; }
    }

    public class Atmosphere
    {
        @SerializedName("humidity")
        @Expose
        private String humidity;

        public String getHumidity() { return this.humidity; }

        public void setHumidity(String humidity) { this.humidity = humidity; }

        @SerializedName("pressure")
        @Expose
        private String pressure;

        public String getPressure() { return this.pressure; }

        public void setPressure(String pressure) { this.pressure = pressure; }

        @SerializedName("rising")
        @Expose
        private String rising;

        public String getRising() { return this.rising; }

        public void setRising(String rising) { this.rising = rising; }

        @SerializedName("visibility")
        @Expose
        private String visibility;

        public String getVisibility() { return this.visibility; }

        public void setVisibility(String visibility) { this.visibility = visibility; }
    }

    public class Astronomy
    {
        @SerializedName("sunrise")
        @Expose
        private String sunrise;

        public String getSunrise() { return this.sunrise; }

        public void setSunrise(String sunrise) { this.sunrise = sunrise; }

        @SerializedName("sunset")
        @Expose
        private String sunset;

        public String getSunset() { return this.sunset; }

        public void setSunset(String sunset) { this.sunset = sunset; }
    }

    public class Image
    {
        @SerializedName("title")
        @Expose
        private String title;

        public String getTitle() { return this.title; }

        public void setTitle(String title) { this.title = title; }

        @SerializedName("width")
        @Expose
        private String width;

        public String getWidth() { return this.width; }

        public void setWidth(String width) { this.width = width; }

        @SerializedName("height")
        @Expose
        private String height;

        public String getHeight() { return this.height; }

        public void setHeight(String height) { this.height = height; }

        @SerializedName("link")
        @Expose
        private String link;

        public String getLink() { return this.link; }

        public void setLink(String link) { this.link = link; }

        @SerializedName("url")
        @Expose
        private String url;

        public String getUrl() { return this.url; }

        public void setUrl(String url) { this.url = url; }
    }

    public class Condition
    {
        @SerializedName("code")
        @Expose
        private String code;

        public String getCode() { return this.code; }

        public void setCode(String code) { this.code = code; }

        @SerializedName("date")
        @Expose
        private String date;

        public String getDate() { return this.date; }

        public void setDate(String date) { this.date = date; }

        @SerializedName("temp")
        @Expose
        private String temp;

        public String getTemp() { return this.temp; }

        public void setTemp(String temp) { this.temp = temp; }

        @SerializedName("text")
        @Expose
        private String text;

        public String getText() { return this.text; }

        public void setText(String text) { this.text = text; }
    }

    public class Forecast
    {
        @SerializedName("code")
        @Expose
        private String code;

        public String getCode() { return this.code; }

        public void setCode(String code) { this.code = code; }

        @SerializedName("date")
        @Expose
        private String date;

        public String getDate() { return this.date; }

        public void setDate(String date) { this.date = date; }

        @SerializedName("day")
        @Expose
        private String day;

        public String getDay() { return this.day; }

        public void setDay(String day) { this.day = day; }

        @SerializedName("high")
        @Expose
        private String high;

        public String getHigh() { return this.high; }

        public void setHigh(String high) { this.high = high; }

        @SerializedName("low")
        @Expose
        private String low;

        public String getLow() { return this.low; }

        public void setLow(String low) { this.low = low; }


        @SerializedName("text")
        @Expose
        private String text;

        public String getText() { return this.text; }

        public void setText(String text) { this.text = text; }
    }

    public class Guid
    {
        @SerializedName("isPermaLink")
        @Expose
        private String isPermaLink;

        public String getIsPermaLink() { return this.isPermaLink; }

        public void setIsPermaLink(String isPermaLink) { this.isPermaLink = isPermaLink; }
    }

    public class Item
    {
        @SerializedName("title")
        @Expose
        private String title;

        public String getTitle() { return this.title; }

        public void setTitle(String title) { this.title = title; }

        @SerializedName("lat")
        @Expose
        private String lat;

        public String getLat() { return this.lat; }

        public void setLat(String lat) { this.lat = lat; }

        @SerializedName("long")
        @Expose
        private String lon;

        public String getLon() { return this.lon; }

        public void setLon(String lon) { this.lon = lon; }

        @SerializedName("link")
        @Expose
        private String link;

        public String getLink() { return this.link; }

        public void setLink(String link) { this.link = link; }

        @SerializedName("pubDate")
        @Expose
        private String pubDate;

        public String getPubDate() { return this.pubDate; }

        public void setPubDate(String pubDate) { this.pubDate = pubDate; }

        @SerializedName("condition")
        @Expose
        private Condition condition;

        public Condition getCondition() { return this.condition; }

        public void setCondition(Condition condition) { this.condition = condition; }

        @SerializedName("forecast")
        @Expose
        private ArrayList<Forecast> forecast;

        public ArrayList<Forecast> getForecast() { return this.forecast; }

        public void setForecast(ArrayList<Forecast> forecast) { this.forecast = forecast; }

        @SerializedName("description")
        @Expose
        private String description;

        public String getDescription() { return this.description; }

        public void setDescription(String description) { this.description = description; }

        @SerializedName("guid")
        @Expose
        private Guid guid;

        public Guid getGuid() { return this.guid; }

        public void setGuid(Guid guid) { this.guid = guid; }
    }

    public class Channel
    {
        @SerializedName("units")
        @Expose
        private Units units;

        public Units getUnits() { return this.units; }

        public void setUnits(Units units) { this.units = units; }

        @SerializedName("title")
        @Expose
        private String title;

        public String getTitle() { return this.title; }

        public void setTitle(String title) { this.title = title; }

        @SerializedName("link")
        @Expose
        private String link;

        public String getLink() { return this.link; }

        public void setLink(String link) { this.link = link; }

        @SerializedName("description")
        @Expose
        private String description;

        public String getDescription() { return this.description; }

        public void setDescription(String description) { this.description = description; }

        @SerializedName("language")
        @Expose
        private String language;

        public String getLanguage() { return this.language; }

        public void setLanguage(String language) { this.language = language; }

        @SerializedName("lastBuildDate")
        @Expose
        private String lastBuildDate;

        public String getLastBuildDate() { return this.lastBuildDate; }

        public void setLastBuildDate(String lastBuildDate) { this.lastBuildDate = lastBuildDate; }

        @SerializedName("ttl")
        @Expose
        private String ttl;

        public String getTtl() { return this.ttl; }

        public void setTtl(String ttl) { this.ttl = ttl; }

        @SerializedName("location")
        @Expose
        private Location location;

        public Location getLocation() { return this.location; }

        public void setLocation(Location location) { this.location = location; }


        @SerializedName("wind")
        @Expose
        private Wind wind;

        public Wind getWind() { return this.wind; }

        public void setWind(Wind wind) { this.wind = wind; }

        @SerializedName("atmosphere")
        @Expose
        private Atmosphere atmosphere;

        public Atmosphere getAtmosphere() { return this.atmosphere; }

        public void setAtmosphere(Atmosphere atmosphere) { this.atmosphere = atmosphere; }


        @SerializedName("astronomy")
        @Expose
        private Astronomy astronomy;

        public Astronomy getAstronomy() { return this.astronomy; }

        public void setAstronomy(Astronomy astronomy) { this.astronomy = astronomy; }

        @SerializedName("image")
        @Expose
        private Image image;

        public Image getImage() { return this.image; }

        public void setImage(Image image) { this.image = image; }

        @SerializedName("item")
        @Expose
        private Item item;

        public Item getItem() { return this.item; }

        public void setItem(Item item) { this.item = item; }
    }

    public class Results
    {
        @SerializedName("channel")
        @Expose
        private Channel channel;

        public Channel getChannel() { return this.channel; }

        public void setChannel(Channel channel) { this.channel = channel; }
    }

    public class Query
    {
        @SerializedName("count")
        @Expose
        private int count;

        public int getCount() { return this.count; }

        public void setCount(int count) { this.count = count; }

        @SerializedName("created")
        @Expose
        private String created;

        public String getCreated() { return this.created; }

        public void setCreated(String created) { this.created = created; }

        @SerializedName("lang")
        @Expose
        private String lang;

        public String getLang() { return this.lang; }

        public void setLang(String lang) { this.lang = lang; }


        @SerializedName("results")
        @Expose
        private Results results;

        public Results getResults() { return this.results; }

        public void setResults(Results results) { this.results = results; }
    }

    @SerializedName("query")
    @Expose
    private Query query;

    public Query getQuery() { return this.query; }

    public void setQuery(Query query) { this.query = query; }

}
