package cloud.artik.example.hellocloud.Util.Retrofit.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 박종현 on 2017-09-16.
 */
public class Recipe {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("type")
    @Expose
    private String type;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Data> data = new ArrayList<>();
    public List<Data> getData(){return data;}
    public class Data {
        @SerializedName("recipeId")
        @Expose
        private int recipe_id;
        @SerializedName("name")
        @Expose
        private String nameSn;
        @SerializedName("summary")
        @Expose
        private String summary;
        @SerializedName("content")
        @Expose
        private String content;
        @SerializedName("typeSn")
        @Expose
        private String typeSn;
        @SerializedName("authorType")
        @Expose
        private String authorType;
        @SerializedName("recipeNum")
        @Expose
        private int recipeNum;
        @SerializedName("imagePath")
        @Expose
        private String imagePath;
        @SerializedName("hitsNum")
        @Expose
        private int hitsNum;
        @SerializedName("likesNum")
        @Expose
        private int likesNum;
        @SerializedName("temperature")
        @Expose
        private int temperature;

        public int getRecipe_id() {
            return recipe_id;
        }

        public void setRecipe_id(int recipe_id) {
            this.recipe_id = recipe_id;
        }

        public String getNameSn() {
            return nameSn;
        }

        public void setNameSn(String nameSn) {
            this.nameSn = nameSn;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTypeSn() {
            return typeSn;
        }

        public void setTypeSn(String typeSn) {
            this.typeSn = typeSn;
        }

        public String getAuthorType() {
            return authorType;
        }

        public void setAuthorType(String authorType) {
            this.authorType = authorType;
        }

        public int getRecipeNum() {
            return recipeNum;
        }

        public void setRecipeNum(int recipeNum) {
            this.recipeNum = recipeNum;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public int getHits() {
            return hitsNum;
        }

        public void setHits(int hits) {
            this.hitsNum = hitsNum;
        }

        public int getLikes() {
            return likesNum;
        }

        public void setLikes(int likes) {
            this.likesNum = likesNum;
        }

        public int getTemperature() {
            return temperature;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

    }
}
