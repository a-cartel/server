//package server.data.dto_temp;
//
//import lombok.Getter;
//
//@Getter
//public class NewsPaging {
//    private int page;
//    private long totalCount;
//    private int totalPage;
//    private boolean hasNext;
//    private boolean hasPrev;
//
//    public NewsPaging(int page, long totalCount, int limit) {
//        this.page = page;
//        this.totalCount = totalCount;
//        this.totalPage = (int) Math.ceil((double) totalCount / limit);
//        this.hasPrev = page > 1;
//        this.hasNext = page < totalPage;
//    }
//}
