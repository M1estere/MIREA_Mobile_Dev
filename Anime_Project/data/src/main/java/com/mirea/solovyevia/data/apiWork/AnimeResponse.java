package com.mirea.solovyevia.data.apiWork;

import com.mirea.solovyevia.domain.models.Anime;

import java.util.List;

public class AnimeResponse {

    private Pagination pagination;
    private List<Anime> data;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<Anime> getData() {
        return data;
    }

    public void setData(List<Anime> data) {
        this.data = data;
    }

    public static class Pagination {
        private int lastVisiblePage;
        private boolean hasNextPage;
        private int currentPage;
        private Items items;

        // Геттеры и сеттеры
        public int getLastVisiblePage() {
            return lastVisiblePage;
        }

        public void setLastVisiblePage(int lastVisiblePage) {
            this.lastVisiblePage = lastVisiblePage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public Items getItems() {
            return items;
        }

        public void setItems(Items items) {
            this.items = items;
        }

        public static class Items {
            private int count;
            private int total;
            private int perPage;

            // Геттеры и сеттеры
            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPerPage() {
                return perPage;
            }

            public void setPerPage(int perPage) {
                this.perPage = perPage;
            }
        }
    }

}
