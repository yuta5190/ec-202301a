/**動的に商品金額が変更するように作成*/
"use strict"
$(function () {
  calc_price();
  $(".size").on("change", function () {
    calc_price();
  });

  $(".checkbox").on("click", function () {
    calc_price();
  });

  $("#toynum").on("change", function () {
    calc_price();
  });

  // 値段の計算をして変更する関数
  function calc_price() {
    let size = $(".size:checked").val();
    let topping_count = $(".checkbox:checked").length;
    let toy_num = $("#toynum").val();
    let size_price = 0;
    let topping_price = 0;
    if (size === "M") {
      size_price = $("#priceM").text();
      topping_price = 200 * topping_count;
    } else {
      size_price = $("#priceL").text();
      topping_price = 300 * topping_count;
    }
    let removeComma = size_price.replace(/,/g, '');
    var num = parseInt(removeComma, 10);
    let price = (num + topping_price) * toy_num;
    $("#totalprice").text(price.toLocaleString());
  }
});
