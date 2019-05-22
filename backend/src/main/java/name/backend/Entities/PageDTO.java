package name.backend.Entities;

import java.util.List;

public class PageDTO {
    private List list;
    private int pages;

    public PageDTO(List productEntityList, int pages) {
        this.list = productEntityList;
        this.pages = pages;
    }

    public List getList() {
        return list;
    }

    public void setList(List productEntityList) {
        this.list = productEntityList;
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
                "productEntityList=" + list +
                ", pages=" + pages +
                '}';
    }
}
