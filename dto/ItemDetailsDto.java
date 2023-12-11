package dto;

public class ItemDetailsDto {
        private String id;
        private String orderId;
        private double unitePrice;
        private int qty;


        public ItemDetailsDto() {
        }

        public ItemDetailsDto( int qty,String id) {
            this.qty = qty;
            this.id = id;

        }

        public ItemDetailsDto(String id, String orderId, double unitePrice, int qty) {
            this.id = id;
            this.orderId = orderId;
            this.unitePrice = unitePrice;
            this.qty = qty;
        }



        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public double getUnitePrice() {
            return unitePrice;
        }

        public void setUnitePrice(double unitePrice) {
            this.unitePrice = unitePrice;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }
    }


