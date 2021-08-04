<template>
  <div class="form-group">
    <label for="reTryContractDates">갱신기간(시작일자, 종료일자)<span class="text-danger"></span></label>
    <div class="form-group row">
      <div class="col-sm-10">
        <input class="form-control" type="text" id="reTryContractDates" name="reTryContractDates" placeholder="" value="">
      </div>
      <div class="col-sm">
        <input type="button" class="form-control btn btn-primary btn-sm" value="재연장" @click="clickRetryRentalBtn">
      </div>
    </div>


  </div>
</template>

<script>
import $ from "jquery";
import moment from "moment";
import axios from "axios";

export default {
  name: "AssetRetryRental",
  props: {
    pBeforeContractDates : String,
    pAssetCode : String,
  },
  data() {
    return {
      beforeContractDates : null,
      assetCode : null,
    }
  },
  watch: {
    pBeforeContractDates() {
      this.beforeContractDates = this.pBeforeContractDates;
    },
    pAssetCode() {
      this.assetCode = this.pAssetCode;
    }
  },
  mounted() {
    this.assetCode = this.pAssetCode;
    this.beforeContractDates = this.pBeforeContractDates;
    this.initReTryContractDatePicker();
  },
  methods: {
    initReTryContractDatePicker() {
      $('input#reTryContractDates').daterangepicker({
        timePicker: true,
        showDropdowns: true,                     // 년월 수동 설정 여부
        autoApply: true,                         // 확인/취소 버튼 사용여부
        startDate: moment().startOf('hour'),
        endDate: moment().startOf('hour').add(32, 'hour'),
        locale : {
          separator: " ~ ",
          format: 'YYYY-MM-DD',
          applyLabel: "적용",
          cancelLabel: "닫기",
          daysOfWeek: ["일", "월", "화", "수", "목", "금", "토"],
          monthNames: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"]
        }
      });
    },
    getReTryData() {
      return  $('input#reTryContractDates').val();
    },
    clickRetryRentalBtn() {
      const vm = this;
      let today = moment().format("YYYY-MM-DD");
      let beforeStartDate = this.beforeContractDates.split("~")[0];
      let beforeEndDate = this.beforeContractDates.split("~")[1];
      let updateStartDate =  $('input#reTryContractDates').val().split("~")[0];
      let updateEndDate =  $('input#reTryContractDates').val().split("~")[1];

      // console.log(beforeStartDate, beforeEndDate, updateStartDate, updateEndDate);

      if(moment(today.toString()).isBetween(beforeStartDate.toString(), beforeEndDate.toString()) === true
          || moment(updateStartDate.toString()).isBefore(beforeStartDate.toString()) === true
          || moment(updateEndDate).isBetween(beforeStartDate.toString(), beforeEndDate.toString()) === true){
        alert("재연장 신청할 수 없습니다.");
        return false;
      }


      axios.put('http://localhost:8080/api/assets/' + this.code + "/retry-rental", {
        code : this.code,
        contractDateAt : updateStartDate,
        expireDateAt : updateEndDate,
      }).then(response => {
        // console.log(response);
        if(response.status === 200) {
          vm.$emit("updateData", response.data);
          $('#asset-update-modal').modal("hide");
        }
      }).catch(e => {
        alert(e);
      })

    }
  }
}
</script>

<style scoped>

</style>