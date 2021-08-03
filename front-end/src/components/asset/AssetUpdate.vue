<template>
  <!-- Modal -->
  <div class="modal fade" id="asset-update-modal" tabindex="-1" aria-labelledby="asset-update-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-xl modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header">
          <h2 class="tit_step">자산수정</h2>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-lg-7">
              <div class="card card-primary card-outline">
                <div class="card-body">
                  <form>
                    <div class="form-group">
                      <label for="nameEdit">자산명<span class="text-danger">*</span></label>
                      <input type="text" class="form-control " id="nameEdit" placeholder="" :value="name">
                    </div>
                    <div class="form-group">
                      <label for="codeEdit">자산코드<span class="text-danger">*</span></label>
                      <div class="form-group row">
                        <div class="col-sm">
                          <input type="text" class="form-control " id="codeEdit" placeholder="" :value="code" disabled readonly="readonly">
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="categorysEdit">카테고리<span class="text-danger">*</span></label>
                      <select class="form-control" style="width: 100%;" id="categorysEdit" disabled readonly="readonly">
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="countEdit">수량<span class="text-danger">*</span></label>
                      <input type="text" class="form-control " id="countEdit" placeholder="" :value="count" disabled readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label for="priceEdit">가격<span class="text-danger"></span></label>
                      <input type="text" class="form-control" id="priceEdit" placeholder="" :value="price" disabled readonly="readonly">
                    </div>
                    <div class="form-group">

                      <div class="form-group row">
                        <div class="col-sm">
                          <label for="buyDateEdit">구매일자<span class="text-danger"></span></label>
                          <input class="form-control" type="text" id="buyDateEdit" placeholder="" :value="buyDate" disabled readonly="readonly">
                        </div>
                        <div class="col-sm">
                          <label for="receiveDateEdit">수령일자<span class="text-danger"></span></label>
                          <input class="form-control" type="text" id="receiveDateEdit" placeholder="" :value="receiveDate" disabled readonly="readonly">
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="membersEdit">담당자<span class="text-danger"></span></label>
                      <select class="form-control" style="width: 100%;" id="membersEdit" disabled readonly="readonly">
                        <option value="">해당없음</option>
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="departmentsEdit">담당부서<span class="text-danger">*</span></label>
                      <select class="form-control" style="width: 100%;" id="departmentsEdit" disabled readonly="readonly">
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="typeEdit">구분<span class="text-danger">*</span></label>
                      <select class="form-control " style="width: 100%;" id="typeEdit" disabled readonly="readonly">
                        <option v-for="(type, index) in types"
                                :key="index"
                                :value="type.code"
                                :selected="index === 0">
                          {{ type.name }}
                        </option>
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="partnerCompanysEdit">업체명<span class="text-danger">*</span></label>
                      <select class="form-control" style="width: 100%;" id="partnerCompanysEdit" disabled readonly="readonly">
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="contractDatesEdit">계약기간(시작일자, 종료일자)<span class="text-danger"></span></label>
                      <input class="form-control" type="text" id="contractDatesEdit" name="contractDatesEdit" placeholder="" :value="contractDate" disabled readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label for="remarkEdit">비고<code></code></label>
                      <textarea class="form-control" id="remarkEdit" :value="remark"></textarea>
                    </div>
                    <div class="form-group">
                      <div class="form-group">
                        <div class="custom-control custom-radio d-inline pr-1">
                          <input class="custom-control-input" type="radio" id="useYnFalseEdit" name="useYnRadioEdit" @click="clickRadioBtn(false)">
                          <label for="useYnFalseEdit" class="custom-control-label">비활성화</label>
                        </div>
                        <div class="custom-control custom-radio d-inline">
                          <input class="custom-control-input" type="radio" id="useYnTrueEdit" name="useYnRadioEdit" checked="" @click="clickRadioBtn(true)">
                          <label for="useYnTrueEdit" class="custom-control-label">활성화</label>
                        </div>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
            </div>


            <div class="col-lg">
              <div class="card card-primary card-outline">
                <div class="card-body">
                  <form>
                    <div class="form-group">
                      <label for="label2999934">모델명<span class="text-danger"></span></label>
                      <input type="text" class="form-control " id="label2999934" placeholder="">
                    </div>
                    <div class="form-group">
                      <label for="label2656334">시리얼넘버<span class="text-danger"></span></label>
                      <input type="text" class="form-control " id="label2656334" placeholder="">
                    </div>
                    <div class="form-group">
                      <label for="label234">CPU<span class="text-danger"></span></label>
                      <input type="text" class="form-control " id="label234" placeholder="">
                    </div>
                    <div class="form-group">
                      <label for="label2343">Memory<span class="text-danger"></span></label>
                      <input type="text" class="form-control " id="label2343" placeholder="">
                    </div>
                    <div class="form-group">
                      <label for="label23435">Memory<span class="text-danger"></span></label>
                      <input type="text" class="form-control " id="label23435" placeholder="">
                    </div>
                  </form>
                </div>
              </div>
            </div>

          </div>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">  <i class="far fa-window-close pr-1"></i>취소</button>
          <button type="button" class="btn btn-primary" @click="updateBtnClick"> <i class="far fa-edit pr-1"></i>수정</button>
          <button type="button" class="btn btn-danger" @click="deleteBtnClick"><i class="far fa-trash-alt pr-1"></i>삭제</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import axios from "axios";
import moment from "moment";

export default {
  name: "AssetUpdate",
  data() {
    return {
      name : null,
      code : null,
      category : null,
      count : null,
      price : null,
      buyDate : null,
      receiveDate : null,
      member : null,
      department : null,
      type : null,
      partnerCompany : null,
      contractDate : null,
      remark : null,
      useYn : null,
      types: [
        {code : 'buy', name : '구매'},
        {code : 'rental', name : '렌탈'},
      ],
    }
  },
  mounted() {
    this.initBuyDatePicker();
    this.initReceiveDatePicker();
    this.initContractDatePicker();
    this.findAll();

    $('#asset-update-modal input#contractDatesEdit').attr("disabled", true);
    $('#asset-update-modal input#receiveDateEdit').attr("disabled", true);
  },
  methods: {
    setData(data) {
      this.name = data.name;
      this.code = data.code;
      this.category = data.categoryId;
      this.count = data.count;
      this.price = data.price;
      this.buyDate = data.buyDateAt;
      this.receiveDate = data.receivedDateAt;
      this.member = data.memberId;
      this.department = data.departmentId;
      this.type = data.type;
      this.partnerCompany = data.partnerCompanyId;
      this.contractDate = data.contractDateAt + " ~ " + data.expireDateAt;
      this.remark = data.remark;
      this.useYn = data.useYn;


      $("#asset-update-modal select#categorys").val(this.category);
      $("#asset-update-modal select#members").val(this.member);
      $("#asset-update-modal select#departments").val(this.department);
      $("#asset-update-modal select#partnerCompanys").val(this.partnerCompany);
      $( "#asset-update-modal input#buyDate" ).val(this.buyDate);
      $( "#asset-update-modal input#receiveDate" ).val(this.receiveDate);
      $("#asset-update-modal input#contractDates").val(this.contractDate);
      if(this.useYn === true) {
        $('#asset-update-modal #useYnTrueEdit').prop("checked", true);
      } else {
        $('#asset-update-modal #useYnFalseEdit').prop("checked", true);
      }
    },
    updateBtnClick() {
      const vm = this;

      if($( '#asset-update-modal input#nameEdit' ).val() ==="") {
        alert("이름을 입력해주세요.");
        return false;
      }

      axios.put('http://localhost:8080/api/assets/' + this.code, {
        name : $( '#asset-update-modal input#nameEdit' ).val(),
        remark : $( '#asset-update-modal textarea#remarkEdit' ).val(),
        useYn : this.useYn,
      }).then(response => {
        // console.log(response);
        if(response.status === 200) {
          // this.$router.push('Authority');
          vm.$emit("updateData", response.data);
          $('#asset-update-modal').modal("hide");
        }
      }).catch(e => {
        alert(e);
      })
    },
    deleteBtnClick() {
      const vm = this;

      if(!confirm(this.name + "을 삭제하시겠습니까?")) {
        return false;
      }

      axios.delete('http://localhost:8080/api/assets/' + this.code)
          .then(response => {
            // console.log(response);
            if(response.status === 200) {
              // this.$router.push('Authority');
              vm.$emit("updateData", response.data);
              $('#asset-update-modal').modal("hide");
            }
          }).catch(e => {
        alert(e);
      })
    },
    clickRadioBtn(flag){
      this.useYn = flag;
    },
    getCategoryInstance() {
      return $("#asset-update-modal #" + "categorysEdit");
    },
    getDepartmentInstance() {
      return $("#asset-update-modal #" + "departmentsEdit");
    },
    getMemberInstance() {
      return $("#asset-update-modal #" + "membersEdit");
    },
    getPartnerCompanyInstance() {
      return $("#asset-update-modal #" + "partnerCompanysEdit");
    },
    findCategorys() {
      return axios.get('http://localhost:8080/api/categorys');
    },
    findCategorySpecifications() {
      return axios.get('http://localhost:8080/api/category-specifications');
    },
    findDepartments() {
      return axios.get('http://localhost:8080/api/departments');
    },
    findMembers() {
      return axios.get('http://localhost:8080/api/members');
    },
    findPartnerCompanys() {
      return axios.get('http://localhost:8080/api/partner-companys');
    },
    findAll() {
      const vm = this;
      axios.all([this.findCategorys(), this.findCategorySpecifications(), this.findDepartments(), this.findMembers(),
        this.findPartnerCompanys()])
          .then(axios.spread(function (resCategorys, specifications, resDepartments, resMembers, resPartnerCompanys) {
            // console.log(resCategorys, specifications, resDepartments, resMembers, resPartnerCompanys);

            let categorysNewDatas = [];
            vm.categorys = resCategorys.data.filter(category => {
              return (category.useYn === true);
            });
            vm.categorys.forEach(category => {
              // console.log(index, 'dddddee')
              categorysNewDatas.push({
                'id' : category.id ,
                'text' : category.name,
                'defaultSelected' : false,
                'selected' : false
              });
            })
            vm.getCategoryInstance().select2({
              dropdownParent: $('#asset-update-modal'),
              data : categorysNewDatas,
            });

            let departmentsNewDatas = [];
            vm.departments = resDepartments.data.filter(department => {
              return department.useYn === true;
            });
            vm.departments.forEach(department => {
              departmentsNewDatas.push({
                'id' : department.id ,
                'text' : department.name,
                'defaultSelected' : false,
                'selected' : false
              })
            })
            vm.getDepartmentInstance().select2({
              dropdownParent: $('#asset-update-modal'),
              data : departmentsNewDatas,
            });

            let membersNewDatas = [];
            vm.members = resMembers.data.filter(member => {
              return member.useYn === true;
            });
            vm.members.forEach(member => {
              membersNewDatas.push({
                'id' : member.id ,
                'text' : member.name,
                'defaultSelected' : false,
                'selected' : false
              })
            })
            vm.getMemberInstance().select2({
              dropdownParent: $('#asset-update-modal'),
              data : membersNewDatas,
            });

            let partnerCompanyNewDatas = [];
            vm.partnerCompanys = resPartnerCompanys.data.filter(partnerCompany => {
              return partnerCompany.useYn === true;
            })
            vm.partnerCompanys.forEach(partnerCompany => {
              partnerCompanyNewDatas.push({
                'id' : partnerCompany.id ,
                'text' : partnerCompany.name,
                'defaultSelected' : false,
                'selected' : false
              })
            })

            vm.getPartnerCompanyInstance().select2({
              dropdownParent: $('#asset-update-modal'),
              data : partnerCompanyNewDatas,
            });

          })).catch(e => {
        alert(e);
      })
    },
    initBuyDatePicker() {
      $('#asset-update-modal input#buyDateEdit').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        minYear: 1901,
        maxYear: parseInt(moment().format('YYYY'),10),
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
    initReceiveDatePicker() {
      $('#asset-update-modal input#receiveDateEdit').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        minYear: 1901,
        maxYear: parseInt(moment().format('YYYY'),10),
        locale : {
          format: 'YYYY-MM-DD',
          applyLabel: "적용",
          cancelLabel: "닫기",
          daysOfWeek: ["일", "월", "화", "수", "목", "금", "토"],
          monthNames: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"]
        }
      });
    },
    initContractDatePicker() {
      $('#asset-update-modal input#contractDatesEdit').daterangepicker({
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
  }
}
</script>

<style scoped>

</style>