package name.backend.Entities;

import java.util.List;

public class PageDTO {
    private List productList;
    private int pages;

    public PageDTO(List productEntityList, int pages) {
        this.productList = productEntityList;
        this.pages = pages;
    }

    public List getProductList() {
        return productList;
    }

    public void setProductList(List productEntityList) {
        this.productList = productEntityList;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "PageDTO{" +
                "productEntityList=" + productList +
                ", pages=" + pages +
                '}';
    }
}
