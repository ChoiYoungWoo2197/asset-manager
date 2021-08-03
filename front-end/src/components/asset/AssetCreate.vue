<template>
  <!-- Modal -->
  <div class="modal fade" id="asset-create-modal" tabindex="-1" aria-labelledby="category-specification-create-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-xl modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header">
          <h2 class="tit_step">자산등록</h2>
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
                      <label for="name">자산명<span class="text-danger">*</span></label>
                      <input type="text" class="form-control " id="name" placeholder="">
                    </div>
                    <div class="form-group">
                      <label for="code">자산코드<span class="text-danger">*</span></label>
                      <div class="form-group row">
                        <div class="col-sm-10">
                          <input type="text" class="form-control " id="code" placeholder="">
                        </div>
                        <div class="col-sm">
                          <input type="button" class="form-control btn btn-primary btn-sm" value="중복체크" @click="clickCodeCheckBtn">
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="categorys">카테고리<span class="text-danger">*</span></label>
                      <select class="form-control" style="width: 100%;" id="categorys">
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="count">수량<span class="text-danger">*</span></label>
                      <input type="text" class="form-control " id="count" placeholder="">
                    </div>
                    <div class="form-group">
                      <label for="price">가격<span class="text-danger"></span></label>
                      <input type="text" class="form-control" id="price" placeholder="">
                    </div>
                    <div class="form-group">

                      <div class="form-group row">
                        <div class="col-sm">
                          <label for="buyDate">구매일자<span class="text-danger"></span></label>
                          <input class="form-control" type="text" id="buyDate" placeholder="">
                        </div>
                        <div class="col-sm">
                          <label for="receiveDate">수령일자<span class="text-danger"></span></label>
                          <input class="form-control" type="text" id="receiveDate" placeholder="">
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="members">담당자<span class="text-danger"></span></label>
                      <select class="form-control" style="width: 100%;" id="members">
                        <option value="">해당없음</option>
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="departments">담당부서<span class="text-danger">*</span></label>
                      <select class="form-control" style="width: 100%;" id="departments">
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="type">구분<span class="text-danger">*</span></label>
                      <select class="form-control " style="width: 100%;" id="type" @change="handleChangeType">
                        <option v-for="(type, index) in types"
                                :key="index"
                                :value="type.code"
                                :selected="index === 0">
                          {{ type.name }}
                        </option>
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="partnerCompanys">업체명<span class="text-danger">*</span></label>
                      <select class="form-control" style="width: 100%;" id="partnerCompanys">
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="contractDates">계약기간(시작일자, 종료일자)<span class="text-danger"></span></label>
                      <input class="form-control" type="text" id="contractDates" name="contractDates" placeholder="">
                    </div>
                    <div class="form-group">
                      <label for="remark">비고<code></code></label>
                      <textarea class="form-control" id="remark"></textarea>
                    </div>
                    <div class="form-group">
                      <div class="form-group">
                        <div class="custom-control custom-radio d-inline pr-1">
                          <input class="custom-control-input" type="radio" id="useYnFalse" name="useYnRadio" @click="clickRadioBtn(false)">
                          <label for="useYnFalse" class="custom-control-label">비활성화</label>
                        </div>
                        <div class="custom-control custom-radio d-inline">
                          <input class="custom-control-input" type="radio" id="useYnTrue" name="useYnRadio" checked="" @click="clickRadioBtn(true)">
                          <label for="useYnTrue" class="custom-control-label">활성화</label>
                        </div>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
            </div>
            <div class="col-lg">
              <div class="card card-primary card-outline">
                <AssetSpecification ref="assetSpecification">
                </AssetSpecification>
              </div>
            </div>

          </div>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal"> <i class="far fa-window-close pr-1"></i>취소</button>
          <button id="createBtn" type="button" class="btn btn-secondary" @click="createBtnClick"><i class="far fa-edit pr-1"></i>등록</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import axios from "axios";
import moment from 'moment';
import AssetSpecification from "@/components/asset/AssetSpecification.vue";

export default {
  name: "AssetCreate",
  components: {
    AssetSpecification
  },
  data() {
    return {
      isExistCode : null,
      useYn : true,
      types: [
        {code : 'buy', name : '구매'},
        {code : 'rental', name : '렌탈'},
      ],
      categorys : [],
      categorySpeicifications : [],
      members : [],
      departments : [],
      partnerCompanys : [],
      activeCategory : null,
    }
  },
  watch: {
    activeCategory() {
      if(this.$refs.assetSpecification !== null) {
        this.$refs.assetSpecification.findChild(this.activeCategory);
      }
    }
  },
  mounted() {
    // this.initCategorysSelect2();
    this.initBuyDatePicker();
    this.initReceiveDatePicker();
    this.initContractDatePicker();
    this.findAll();
    $('#asset-create-modal input#contractDates').attr("disabled", true);
  },
  updated() {
    // this.findCategorysAndSpecificationsAndDepartments();
  },
  methods: {
    clearData() {
      $( '#asset-create-modal input#code' ).val("");
      $( '#asset-create-modal input#name' ).val("");
      $("#asset-create-modal #categorys").val("");
      $("#asset-create-modal #members").val("");
      $("#asset-create-modal #departments").val("")
      $("#asset-create-modal #partnerCompanys").val("")
      $( '#asset-create-modal input#count' ).val("")
      $( '#asset-create-modal input#price' ).val("");
      $( '#asset-create-modal select#type' ).val("buy")
      $( '#asset-create-modal #buyDate' ).val("")
      $( '#asset-create-modal #receiveDate' ).val("")
      $( '#asset-create-modal textarea#remark' ).val("");
      $('#asset-create-modal button#createBtn').addClass('btn-primary');
      $('#asset-create-modal button#createBtn').addClass('btn-secondary');

      $('#asset-create-modal input#buyDate').attr("disabled", false);
      $('#asset-create-modal input#contractDates').attr("disabled", false);
      $('#asset-create-modal input#contractDates').attr("disabled", true);

      this.isExistCode = null;
      this.useYn = true;
    },
    getCategoryInstance() {
      return $("#asset-create-modal #" + "categorys");
    },
    getDepartmentInstance() {
      return $("#asset-create-modal #" + "departments");
    },
    getMemberInstance() {
      return $("#asset-create-modal #" + "members");
    },
    getPartnerCompanyInstance() {
      return $("#asset-create-modal #" + "partnerCompanys");
    },
    initCategorysSelect2() {
      const vm = this;
      const $categorys = vm.getCategoryInstance();

      $categorys.select2({
        theme: 'bootstrap4',
      });
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
          dropdownParent: $('#asset-create-modal'),
          data : categorysNewDatas,
        }).on("select2:select", function (e) {
          var data = e.params.data;
          // console.log(data);
          vm.activeCategory = data;
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
          dropdownParent: $('#asset-create-modal'),
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
          dropdownParent: $('#asset-create-modal'),
          data : membersNewDatas,
        });

        let partnerCompanyNewDatas = [];
        vm.partnerCompanys = resPartnerCompanys.data.filter(partnerCompany => {
          return partnerCompany.useYn === true;
        })
        vm.partnerCompanys.filter(partnerCompany => {
          return partnerCompany.type === 'buy';
        }).forEach(partnerCompany => {
          partnerCompanyNewDatas.push({
            'id' : partnerCompany.id ,
            'text' : partnerCompany.name,
            'defaultSelected' : false,
            'selected' : false
          })
        })

        vm.getPartnerCompanyInstance().select2({
          dropdownParent: $('#asset-create-modal'),
          data : partnerCompanyNewDatas,
        });

      })).catch(e => {
        alert(e);
      })
    },
    clickRadioBtn(flag){
      this.useYn = flag;
    },
    handleChangeType() {
      this.getPartnerCompanyInstance().empty();

      $('#asset-create-modal input#buyDate').attr("disabled", false);
      $('#asset-create-modal input#contractDates').attr("disabled", false);


      let datas = [];
      if($('#type').val() === "buy") {
        datas = this.partnerCompanys.filter(partnerCompany => {
          return partnerCompany.type === "buy";
        })

        $('#asset-create-modal input#contractDates').attr("disabled", true);

      } else {
        datas = this.partnerCompanys.filter(partnerCompany => {
          return partnerCompany.type === "rental";
        })

        $('#asset-create-modal input#buyDate').attr("disabled", true);
      }
      datas.forEach(data => {
        var newOption = new Option(data.name, data.id, false, false);
        this.getPartnerCompanyInstance().append(newOption).trigger('change');
      })
    },
    initBuyDatePicker() {
      $('#asset-create-modal input#buyDate').daterangepicker({
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
      $('#asset-create-modal input#receiveDate').daterangepicker({
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
      $('#asset-create-modal input#contractDates').daterangepicker({
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
    clickCodeCheckBtn() {
      const vm = this;
      if($( '#asset-create-modal input#code' ).val() === "") {
        alert("코드를 입력해주세요.");
        return false;
      }

      axios.get('http://localhost:8080/api/assets/' + $( '#asset-create-modal input#code' ).val() + '/exists')
          .then(response => {
            if(response.status === 200) {
              if(response.data === true) {
                vm.isExistCode = true;
                alert("중복된 코드입니다.");
              } else {
                vm.isExistCode = false;
                $('#asset-create-modal button#createBtn').removeClass('btn-secondary');
                $('#asset-create-modal button#createBtn').addClass('btn-primary');
                alert("사용 가능한 코드입니다.");
              }
            }
          }).catch(e => {
        alert(e);
      })
    },
    createBtnClick() {
      const vm = this;
      if($( '#asset-create-modal input#code' ).val() === "" || $( '#asset-create-modal input#name' ).val() === ""
          || $("#asset-create-modal #categorys").val() === "" || $("#asset-create-modal #categorys").val() == null
          || $( '#asset-create-modal #departments' ).val() === "" || $( '#asset-create-modal #departments' ).val() === null
          || $( '#asset-create-modal #partnerCompanys' ).val() === "" || $( '#asset-create-modal #partnerCompanys' ).val() === null
          || $( '#asset-create-modal input#count' ).val() === "" || this.isExistCode !== false) {
        if($( '#asset-create-modal input#code' ).val() === '') {
          alert("코드를 입력해주세요.");
          return false;
        } else if($( '#asset-create-modal input#name' ).val() === '') {
          alert("이름을 입력해주세요.");
          return false;
        } else if($("#asset-create-modal #categorys").val() === "" || $("#asset-create-modal #categorys").val() === null) {
          alert("카테고리를 선택해주세요.");
          return false;
        } else if($( '#asset-create-modal input#count' ).val() === "") {
          alert("수량을 선택해주세요.");
          return false;
        } else if($( '#asset-create-modal #departments' ).val() === "" || $( '#asset-create-modal #departments' ).val() === null) {
          alert("부서를 선택해주세요.");
          return false;
        } else if($( '#asset-create-modal #partnerCompanys' ).val() === "" || $( '#asset-create-modal #partnerCompanys' ).val() === null) {
          alert("업체를 선택해주세요.");
          return false;
        } else if( this.isExistCode !== false ) {
          //제일 마지막에 추가해주자.
          alert('코드 중복체크를 해주세요.');
          return false;
        }
      }


      axios.post('http://localhost:8080/api/assets', {
        code : $( '#asset-create-modal input#code' ).val(),
        name : $( '#asset-create-modal input#name' ).val(),
        categoryId : $("#asset-create-modal #categorys").val(),
        memberId :  $("#asset-create-modal #members").val(),
        departmentId : $("#asset-create-modal #departments").val(),
        partnerCompanyId : $("#asset-create-modal #partnerCompanys").val(),
        count : $( '#asset-create-modal input#count' ).val(),
        price : $( '#asset-create-modal input#price' ).val(),
        type : $( '#asset-create-modal select#type' ).val(),
        buyDateAt : $( '#asset-create-modal #buyDate' ).val(),
        receivedDateAt : $( '#asset-create-modal #receiveDate' ).val(),
        contractDateAt : $( '#asset-create-modal #contractDates' ).val().split("~")[0].trim(),
        expireDateAt : $( '#asset-create-modal #contractDates' ).val().split("~")[1].trim(),
        remark : $( '#asset-create-modal textarea#remark' ).val(),
        useYn : this.useYn,
        register : "1"
      }).then(response => {
        // console.log(response);
        if(response.status === 200) {
          let specs = this.$refs.assetSpecification.getSpecificationDatas();
          specs.forEach(spec => {
            spec.assetCode = response.data.code;
          })


          if(response.status === 200) {
            axios.post('http://localhost:8080/api/asset-specifications', specs
            ).then(response2 => {
              if(response2.status === 200) {
                // console.log(response2, '2222');
                vm.$emit("updateData", response.data);
                $('#asset-create-modal').modal("hide");
              }
            }).catch(e2 =>{
              alert(e2);
            });
          }
        }
      }).catch(e => {
        alert(e);
      })
    },
    testClick() {
      console.log(
          this.$refs.assetSpecification.getSpecificationDatas()
      );
    }
  }
}
</script>

<style scoped>

</style>