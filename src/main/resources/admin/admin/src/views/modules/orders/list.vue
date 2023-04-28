<template>
  <div class="main-content">
    <!-- 列表页 -->
    <div v-if="showFlag">
      <el-form :inline="true" :model="searchForm" class="form-content">
        <el-row  :gutter="20" class="slt" :style="{justifyContent:contents.searchBoxPosition=='1'?'flex-start':contents.searchBoxPosition=='2'?'center':'flex-end'}">
                <el-form-item :label="contents.inputTitle == 1 ? '订单编号' : ''">
                  <el-input v-if="contents.inputIcon == 1 && contents.inputIconPosition == 1" prefix-icon="el-icon-search" v-model="searchForm.orderid" placeholder="订单编号" clearable></el-input>
                  <el-input v-if="contents.inputIcon == 1 && contents.inputIconPosition == 2" suffix-icon="el-icon-search" v-model="searchForm.orderid" placeholder="订单编号" clearable></el-input>
                  <el-input v-if="contents.inputIcon == 0" v-model="searchForm.orderid" placeholder="订单编号" clearable></el-input>
                </el-form-item>
                <el-form-item :label="contents.inputTitle == 1 ? '商品名称' : ''">
                  <el-input v-if="contents.inputIcon == 1 && contents.inputIconPosition == 1" prefix-icon="el-icon-search" v-model="searchForm.goodname" placeholder="商品名称" clearable></el-input>
                  <el-input v-if="contents.inputIcon == 1 && contents.inputIconPosition == 2" suffix-icon="el-icon-search" v-model="searchForm.goodname" placeholder="商品名称" clearable></el-input>
                  <el-input v-if="contents.inputIcon == 0" v-model="searchForm.goodname" placeholder="商品名称" clearable></el-input>
                </el-form-item>
          <el-form-item>
            <el-button v-if="contents.searchBtnIcon == 1 && contents.searchBtnIconPosition == 1" icon="el-icon-search" type="success" @click="search()">{{ contents.searchBtnFont == 1?'查询':'' }}</el-button>
            <el-button v-if="contents.searchBtnIcon == 1 && contents.searchBtnIconPosition == 2" type="success" @click="search()">{{ contents.searchBtnFont == 1?'查询':'' }}<i class="el-icon-search el-icon--right"/></el-button>
            <el-button v-if="contents.searchBtnIcon == 0" type="success" @click="search()">{{ contents.searchBtnFont == 1?'查询':'' }}</el-button>
          </el-form-item>
        </el-row>

        <el-row class="ad" :style="{justifyContent:contents.btnAdAllBoxPosition=='1'?'flex-start':contents.btnAdAllBoxPosition=='2'?'center':'flex-end'}">
          <el-form-item>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'新增') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 1"
              type="success"
              icon="el-icon-plus"
              @click="addOrUpdateHandler()"
            >{{ contents.btnAdAllFont == 1?'新增':'' }}</el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'新增') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 2"
              type="success"
              @click="addOrUpdateHandler()"
            >{{ contents.btnAdAllFont == 1?'新增':'' }}<i class="el-icon-plus el-icon--right" /></el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'新增') && contents.btnAdAllIcon == 0"
              type="success"
              @click="addOrUpdateHandler()"
            >{{ contents.btnAdAllFont == 1?'新增':'' }}</el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'删除') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 1 && contents.tableSelection"
              :disabled="dataListSelections.length <= 0"
              type="danger"
              icon="el-icon-delete"
              @click="deleteHandler()"
            >{{ contents.btnAdAllFont == 1?'删除':'' }}</el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'删除') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 2 && contents.tableSelection"
              :disabled="dataListSelections.length <= 0"
              type="danger"
              @click="deleteHandler()"
            >{{ contents.btnAdAllFont == 1?'删除':'' }}<i class="el-icon-delete el-icon--right" /></el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'删除') && contents.btnAdAllIcon == 0 && contents.tableSelection"
              :disabled="dataListSelections.length <= 0"
              type="danger"
              @click="deleteHandler()"
            >{{ contents.btnAdAllFont == 1?'删除':'' }}</el-button>


	    <download-excel v-if="isAuth('orders'+'/'+orderStatus,'导出')" class = "export-excel-wrapper" :data = "dataList" :fields = "json_fields" name = "订单.xls">
		      <!-- 导出excel -->
            	<el-button
              	v-if="contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 1"
              	type="danger"
              	icon="el-icon-download"
            	>{{ contents.btnAdAllFont == 1?'导出':'' }}</el-button>
            	<el-button
              	v-if="contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 2"
              	type="danger"
            	>{{ contents.btnAdAllFont == 1?'导出':'' }}<i class="el-icon-download el-icon--right" /></el-button>
            	<el-button
              	v-if="contents.btnAdAllIcon == 0"
              	type="danger"
            	>{{ contents.btnAdAllFont == 1?'导出':'' }}</el-button>
       	    </download-excel>


            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'日销量') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 1"
              type="warning"
              icon="el-icon-s-data"
              @click="dayNumberChartDialog()"
            >{{ contents.btnAdAllFont == 1?'日销量':'' }}</el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'日销量') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 2"
              type="warning"
              @click="dayNumberChartDialog()"
            >{{ contents.btnAdAllFont == 1?'日销量':'' }}<i class="el-icon-s-data el-icon--right" /></el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'日销量') && contents.btnAdAllIcon == 0"
              type="warning"
              @click="dayNumberChartDialog()"
            >{{ contents.btnAdAllFont == 1?'日销量':'' }}</el-button>

            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'月销量') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 1"
              type="warning"
              icon="el-icon-s-data"
              @click="monthNumberChartDialog()"
            >{{ contents.btnAdAllFont == 1?'月销量':'' }}</el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'月销量') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 2"
              type="warning"
              @click="monthNumberChartDialog()"
            >{{ contents.btnAdAllFont == 1?'月销量':'' }}<i class="el-icon-s-data el-icon--right" /></el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'月销量') && contents.btnAdAllIcon == 0"
              type="warning"
              @click="monthNumberChartDialog()"
            >{{ contents.btnAdAllFont == 1?'月销量':'' }}</el-button>
            
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'年销量') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 1"
              type="warning"
              icon="el-icon-s-data"
              @click="yearNumberChartDialog()"
            >{{ contents.btnAdAllFont == 1?'年销量':'' }}</el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'年销量') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 2"
              type="warning"
              @click="yearNumberChartDialog()"
            >{{ contents.btnAdAllFont == 1?'年销量':'' }}<i class="el-icon-s-data el-icon--right" /></el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'年销量') && contents.btnAdAllIcon == 0"
              type="warning"
              @click="yearNumberChartDialog()"
            >{{ contents.btnAdAllFont == 1?'年销量':'' }}</el-button>
            
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'品销量') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 1"
              type="warning"
              icon="el-icon-s-data"
              @click="goodnameNumberChartDialog()"
            >{{ contents.btnAdAllFont == 1?'商品销量':'' }}</el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'品销量') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 2"
              type="warning"
              @click="goodnameNumberChartDialog()"
            >{{ contents.btnAdAllFont == 1?'商品销量':'' }}<i class="el-icon-s-data el-icon--right" /></el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'品销量') && contents.btnAdAllIcon == 0"
              type="warning"
              @click="goodnameNumberChartDialog()"
            >{{ contents.btnAdAllFont == 1?'商品销量':'' }}</el-button>
            
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'类销量') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 1"
              type="warning"
              icon="el-icon-s-data"
              @click="goodtypeNumberChartDialog()"
            >{{ contents.btnAdAllFont == 1?'类型销量':'' }}</el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'类销量') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 2"
              type="warning"
              @click="goodtypeNumberChartDialog()"
            >{{ contents.btnAdAllFont == 1?'类型销量':'' }}<i class="el-icon-s-data el-icon--right" /></el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'类销量') && contents.btnAdAllIcon == 0"
              type="warning"
              @click="goodtypeNumberChartDialog()"
            >{{ contents.btnAdAllFont == 1?'类型销量':'' }}</el-button>
            
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'日销额') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 1"
              type="warning"
              icon="el-icon-s-data"
              @click="dayAmountChartDialog()"
            >{{ contents.btnAdAllFont == 1?'日销额':'' }}</el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'日销额') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 2"
              type="warning"
              @click="dayAmountChartDialog()"
            >{{ contents.btnAdAllFont == 1?'日销额':'' }}<i class="el-icon-s-data el-icon--right" /></el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'日销额') && contents.btnAdAllIcon == 0"
              type="warning"
              @click="dayAmountChartDialog()"
            >{{ contents.btnAdAllFont == 1?'日销额':'' }}</el-button>
            
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'月销额') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 1"
              type="warning"
              icon="el-icon-s-data"
              @click="monthAmountChartDialog()"
            >{{ contents.btnAdAllFont == 1?'月销额':'' }}</el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'月销额') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 2"
              type="warning"
              @click="monthAmountChartDialog()"
            >{{ contents.btnAdAllFont == 1?'月销额':'' }}<i class="el-icon-s-data el-icon--right" /></el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'月销额') && contents.btnAdAllIcon == 0"
              type="warning"
              @click="monthAmountChartDialog()"
            >{{ contents.btnAdAllFont == 1?'月销额':'' }}</el-button>
            
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'年销额') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 1"
              type="warning"
              icon="el-icon-s-data"
              @click="yearAmountChartDialog()"
            >{{ contents.btnAdAllFont == 1?'年销额':'' }}</el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'年销额') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 2"
              type="warning"
              @click="yearAmountChartDialog()"
            >{{ contents.btnAdAllFont == 1?'年销额':'' }}<i class="el-icon-s-data el-icon--right" /></el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'年销额') && contents.btnAdAllIcon == 0"
              type="warning"
              @click="yearAmountChartDialog()"
            >{{ contents.btnAdAllFont == 1?'年销额':'' }}</el-button>
            
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'品销额') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 1"
              type="warning"
              icon="el-icon-s-data"
              @click="goodnameAmountChartDialog()"
            >{{ contents.btnAdAllFont == 1?'商品销额':'' }}</el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'品销额') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 2"
              type="warning"
              @click="goodnameAmountChartDialog()"
            >{{ contents.btnAdAllFont == 1?'商品销额':'' }}<i class="el-icon-s-data el-icon--right" /></el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'品销额') && contents.btnAdAllIcon == 0"
              type="warning"
              @click="goodnameAmountChartDialog()"
            >{{ contents.btnAdAllFont == 1?'商品销额':'' }}</el-button>
            
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'类销额') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 1"
              type="warning"
              icon="el-icon-s-data"
              @click="goodtypeAmountChartDialog()"
            >{{ contents.btnAdAllFont == 1?'类型销额':'' }}</el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'类销额') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 2"
              type="warning"
              @click="goodtypeAmountChartDialog()"
            >{{ contents.btnAdAllFont == 1?'类型销额':'' }}<i class="el-icon-s-data el-icon--right" /></el-button>
            <el-button
              v-if="isAuth('orders'+'/'+orderStatus,'类销额') && contents.btnAdAllIcon == 0"
              type="warning"
              @click="goodtypeAmountChartDialog()"
            >{{ contents.btnAdAllFont == 1?'类型销额':'' }}</el-button>

          </el-form-item>
        </el-row>
      </el-form>
      <div class="table-content">
        <el-table class="tables" :size="contents.tableSize" :show-header="contents.tableShowHeader"
            :header-row-style="headerRowStyle" :header-cell-style="headerCellStyle"
            :border="contents.tableBorder"
            :fit="contents.tableFit"
            :stripe="contents.tableStripe"
            :style="{width: '100%',fontSize:contents.tableContentFontSize,color:contents.tableContentFontColor}"
            v-if="isAuth('orders'+'/'+orderStatus,'查看')"
            :data="dataList"
            v-loading="dataListLoading"
            @selection-change="selectionChangeHandler">
            <el-table-column  v-if="contents.tableSelection"
                type="selection"
                :header-align="contents.tableAlign"
                align="center"
                width="50">
            </el-table-column>
            <el-table-column label="索引" :align="contents.tableAlign"  v-if="contents.tableIndex" type="index" width="50" />
                <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign" 
                    prop="orderid"
                   :header-align="contents.tableAlign"
		    label="订单编号">
		     <template slot-scope="scope">
                       {{scope.row.orderid}}
                     </template>
                </el-table-column>
                <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign" 
                    prop="goodname"
                   :header-align="contents.tableAlign"
		    label="商品名称">
		     <template slot-scope="scope">
                       {{scope.row.goodname}}
                     </template>
                </el-table-column>
                  <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"  prop="picture"
                   :header-align="contents.tableAlign"
                    width="200"
                    label="商品图片">
                    <template slot-scope="scope">
                      <div v-if="scope.row.picture">
                        <img :src="$base.url+scope.row.picture.split(',')[0]" width="100" height="100">
                      </div>
                      <div v-else>无图片</div>
                    </template>
                  </el-table-column>
                <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign" 
                    prop="buynumber"
                   :header-align="contents.tableAlign"
		    label="购买数量">
		     <template slot-scope="scope">
                       {{scope.row.buynumber}}
                     </template>
                </el-table-column>
                <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign" 
                    prop="price"
                   :header-align="contents.tableAlign"
		    label="价格">
		     <template slot-scope="scope">
                       {{scope.row.price}}
                     </template>
                </el-table-column>
                <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign" 
                    prop="discountprice"
                   :header-align="contents.tableAlign"
		    label="折扣价格">
		     <template slot-scope="scope">
                       {{scope.row.discountprice}}
                     </template>
                </el-table-column>
                <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign" 
                    prop="total"
                   :header-align="contents.tableAlign"
		    label="总价格">
		     <template slot-scope="scope">
                       {{scope.row.total}}
                     </template>
                </el-table-column>
                <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign" 
                    prop="discounttotal"
                   :header-align="contents.tableAlign"
		    label="折扣总价格">
		     <template slot-scope="scope">
                       {{scope.row.discounttotal}}
                     </template>
                </el-table-column>
                <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign" 
                    prop="type"
                   :header-align="contents.tableAlign"
		    :formatter="orderStatusFormatter"
		    label="支付类型">
                </el-table-column>
                <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign" 
                    prop="status"
                   :header-align="contents.tableAlign"
		    label="状态">
		     <template slot-scope="scope">
                       {{scope.row.status}}
                     </template>
                </el-table-column>
                <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign" 
                    prop="address"
                   :header-align="contents.tableAlign"
		    label="地址">
		     <template slot-scope="scope">
                       {{scope.row.address}}
                     </template>
                </el-table-column>
                <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign" 
                    prop="tel"
                   :header-align="contents.tableAlign"
		    label="电话">
		     <template slot-scope="scope">
                       {{scope.row.tel}}
                     </template>
                </el-table-column>
                <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign" 
                    prop="consignee"
                   :header-align="contents.tableAlign"
		    label="收货人">
		     <template slot-scope="scope">
                       {{scope.row.consignee}}
                     </template>
                </el-table-column>
                <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign" 
                    prop="maijiazhanghao"
                   :header-align="contents.tableAlign"
		    label="商户名称">
		     <template slot-scope="scope">
                       {{scope.row.maijiazhanghao}}
                     </template>
                </el-table-column>
		<el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign" 
                    prop="tel"
                   :header-align="contents.tableAlign"
                    label="下单时间">
                     <template slot-scope="scope">
                       {{scope.row.addtime}}
                     </template>
                </el-table-column>
            <el-table-column width="300" :align="contents.tableAlign" 
               :header-align="contents.tableAlign"
                label="操作">
                <template slot-scope="scope">
                <el-button v-if="isAuth('orders'+'/'+orderStatus,'查看') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 1" type="success" icon="el-icon-tickets" size="mini" @click="addOrUpdateHandler(scope.row.id,'info')">{{ contents.tableBtnFont == 1?'详情':'' }}</el-button>
                <el-button v-if="isAuth('orders'+'/'+orderStatus,'查看') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 2" type="success" size="mini" @click="addOrUpdateHandler(scope.row.id,'info')">{{ contents.tableBtnFont == 1?'详情':'' }}<i class="el-icon-tickets el-icon--right" /></el-button>
                <el-button v-if="isAuth('orders'+'/'+orderStatus,'查看') && contents.tableBtnIcon == 0" type="success" size="mini" @click="addOrUpdateHandler(scope.row.id,'info')">{{ contents.tableBtnFont == 1?'详情':'' }}</el-button>
                <el-button v-if=" isAuth('orders'+'/'+orderStatus,'修改') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 1" type="primary" icon="el-icon-edit" size="mini" @click="addOrUpdateHandler(scope.row.id)">{{ contents.tableBtnFont == 1?'修改':'' }}</el-button>
                <el-button v-if=" isAuth('orders'+'/'+orderStatus,'修改') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 2" type="primary" size="mini" @click="addOrUpdateHandler(scope.row.id)">{{ contents.tableBtnFont == 1?'修改':'' }}<i class="el-icon-edit el-icon--right" /></el-button>
                <el-button v-if=" isAuth('orders'+'/'+orderStatus,'修改') && contents.tableBtnIcon == 0" type="primary" size="mini" @click="addOrUpdateHandler(scope.row.id)">{{ contents.tableBtnFont == 1?'修改':'' }}</el-button>

                <el-button v-if="isAuth('orders'+'/'+orderStatus,'物流') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 1" type="primary" icon="el-icon-edit" size="mini" @click="logisticsUpdate(scope.row.id)">{{ contents.tableBtnFont == 1?'物流':'' }}</el-button>
                <el-button v-if="isAuth('orders'+'/'+orderStatus,'物流') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 2" type="primary" size="mini" @click="logisticsUpdate(scope.row.id)">{{ contents.tableBtnFont == 1?'物流':'' }}<i class="el-icon-edit el-icon--right" /></el-button>
                <el-button v-if="isAuth('orders'+'/'+orderStatus,'物流') && contents.tableBtnIcon == 0" type="primary" size="mini" @click="logisticsUpdate(scope.row.id)">{{ contents.tableBtnFont == 1?'物流':'' }}</el-button>
                <el-button v-if="isAuth('orders'+'/'+orderStatus,'发货') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 1" type="primary" icon="el-icon-edit" size="mini" @click="updateHandler(scope.row)">{{ contents.tableBtnFont == 1?'发货':'' }}</el-button>
                <el-button v-if="isAuth('orders'+'/'+orderStatus,'发货') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 2" type="primary" size="mini" @click="updateHandler(scope.row)">{{ contents.tableBtnFont == 1?'发货':'' }}<i class="el-icon-edit el-icon--right" /></el-button>
                <el-button v-if="isAuth('orders'+'/'+orderStatus,'发货') && contents.tableBtnIcon == 0" type="primary" size="mini" @click="updateHandler(scope.row)">{{ contents.tableBtnFont == 1?'发货':'' }}</el-button>

                <el-button v-if="isAuth('orders'+'/'+orderStatus,'确认收货') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 1" type="primary" icon="el-icon-edit" size="mini" @click="updateHandler2(scope.row)">{{ contents.tableBtnFont == 1?'确认收货':'' }}</el-button>
                <el-button v-if="isAuth('orders'+'/'+orderStatus,'确认收货') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 2" type="primary" size="mini" @click="updateHandler2(scope.row)">{{ contents.tableBtnFont == 1?'确认收货':'' }}<i class="el-icon-edit el-icon--right" /></el-button>
                <el-button v-if="isAuth('orders'+'/'+orderStatus,'确认收货') && contents.tableBtnIcon == 0" type="primary" size="mini" @click="updateHandler2(scope.row)">{{ contents.tableBtnFont == 1?'确认收货':'' }}</el-button>



                <el-button v-if="isAuth('orders'+'/'+orderStatus,'删除') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 1" type="danger" icon="el-icon-delete" size="mini" @click="deleteHandler(scope.row.id)">{{ contents.tableBtnFont == 1?'删除':'' }}</el-button>
                <el-button v-if="isAuth('orders'+'/'+orderStatus,'删除') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 2" type="danger" size="mini" @click="deleteHandler(scope.row.id)">{{ contents.tableBtnFont == 1?'删除':'' }}<i class="el-icon-delete el-icon--right" /></el-button>
                <el-button v-if="isAuth('orders'+'/'+orderStatus,'删除') && contents.tableBtnIcon == 0" type="danger" size="mini" @click="deleteHandler(scope.row.id)">{{ contents.tableBtnFont == 1?'删除':'' }}</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
          clsss="pages"
          :layout="layouts"
          @size-change="sizeChangeHandle"
          @current-change="currentChangeHandle"
          :current-page="pageIndex"
          :page-sizes="[10, 20, 50, 100]"
          :page-size="Number(contents.pageEachNum)"
          :total="totalPage"
          :small="contents.pageStyle"
          class="pagination-content"
          :background="contents.pageBtnBG"
          :style="{textAlign:contents.pagePosition==1?'left':contents.pagePosition==2?'center':'right'}"
        ></el-pagination>
      </div>
    </div>
    <!-- 添加/修改页面  将父组件的search方法传递给子组件-->
    <add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>





    <el-dialog
      title="日销量"
      :visible.sync="dayNumberChartVisiable"
      width="800">
        <div id="dayNumberChart" style="width:100%;height:600px;"></div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dayNumberChartDialog">返回</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="月销量"
      :visible.sync="monthNumberChartVisiable"
      width="800">
        <div id="monthNumberChart" style="width:100%;height:600px;"></div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="monthNumberChartDialog">返回</el-button>
      </span>
    </el-dialog>
    
    <el-dialog
      title="年销量"
      :visible.sync="yearNumberChartVisiable"
      width="800">
        <div id="yearNumberChart" style="width:100%;height:600px;"></div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="yearNumberChartDialog">返回</el-button>
      </span>
    </el-dialog>
    
    <el-dialog
      title="商品销量"
      :visible.sync="goodnameNumberChartVisiable"
      width="800">
        <div id="goodnameNumberChart" style="width:100%;height:600px;"></div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="goodnameNumberChartDialog">返回</el-button>
      </span>
    </el-dialog>
    
    <el-dialog
      title="类型销量"
      :visible.sync="goodtypeNumberChartVisiable"
      width="800">
        <div id="goodtypeNumberChart" style="width:100%;height:600px;"></div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="goodtypeNumberChartDialog">返回</el-button>
      </span>
    </el-dialog>
    
    <el-dialog
      title="日销额"
      :visible.sync="dayAmountChartVisiable"
      width="800">
        <div id="dayAmountChart" style="width:100%;height:600px;"></div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dayAmountChartDialog">返回</el-button>
      </span>
    </el-dialog>
    
    <el-dialog
      title="月销额"
      :visible.sync="monthAmountChartVisiable"
      width="800">
        <div id="monthAmountChart" style="width:100%;height:600px;"></div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="monthAmountChartDialog">返回</el-button>
      </span>
    </el-dialog>
    
    <el-dialog
      title="年销额"
      :visible.sync="yearAmountChartVisiable"
      width="800">
        <div id="yearAmountChart" style="width:100%;height:600px;"></div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="yearAmountChartDialog">返回</el-button>
      </span>
    </el-dialog>
    
    <el-dialog
      title="商品销额"
      :visible.sync="goodnameAmountChartVisiable"
      width="800">
        <div id="goodnameAmountChart" style="width:100%;height:600px;"></div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="goodnameAmountChartDialog">返回</el-button>
      </span>
    </el-dialog>
    
    <el-dialog
      title="类型销额"
      :visible.sync="goodtypeAmountChartVisiable"
      width="800">
        <div id="goodtypeAmountChart" style="width:100%;height:600px;"></div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="goodtypeAmountChartDialog">返回</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import axios from 'axios'
import AddOrUpdate from "./add-or-update";
export default {
  data() {
    return {
      searchForm: {
        key: ""
      },
      form:{},
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      showFlag: true,
      sfshVisiable: false,
      shForm: {},
      chartVisiable: false,
      dayNumberChartVisiable: false,
      monthNumberChartVisiable: false,
      yearNumberChartVisiable: false,
      goodnameNumberChartVisiable: false,
      goodtypeNumberChartVisiable: false,
      dayAmountChartVisiable: false,
      monthAmountChartVisiable: false,
      yearAmountChartVisiable: false,
      goodnameAmountChartVisiable: false,
      goodtypeAmountChartVisiable: false,
      addOrUpdateFlag:false,
      contents:{"searchBtnFontColor":"rgba(0, 0, 0, 1)","pagePosition":"2","inputFontSize":"14px","inputBorderRadius":"20px","tableBtnDelFontColor":"rgba(0, 0, 0, 1)","tableBtnIconPosition":"1","searchBtnHeight":"42px","tableBgColor":"rgba(255, 255, 255, 1)","inputIconColor":"rgba(0, 0, 0, 1)","searchBtnBorderRadius":"20px","tableStripe":false,"btnAdAllWarnFontColor":"rgba(0, 0, 0, 1)","tableBtnDelBgColor":"rgba(56, 182, 230, 1)","searchBtnIcon":"1","tableSize":"medium","searchBtnBorderStyle":"double","text":{"padding":"0","boxShadow":"0 0 0px rgba(0,0,0,.1)","margin":"0 auto","borderColor":"rgba(0,0,0,.3)","backgroundColor":"rgba(247, 247, 247, 0)","color":"rgba(0, 0, 0, 1)","borderRadius":"0","borderWidth":"0","width":"650px","lineHeight":"50px","fontSize":"26px","borderStyle":"solid"},"tableSelection":true,"searchBtnBorderWidth":"5px 000","tableContentFontSize":"14px","searchBtnBgColor":"#fff","inputTitleSize":"15px","btnAdAllBorderColor":"rgba(56, 182, 230, 1)","pageJumper":true,"btnAdAllIconPosition":"1","searchBoxPosition":"2","tableBtnDetailFontColor":"rgba(0, 0, 0, 1)","tableBtnHeight":"40px","pagePager":true,"searchBtnBorderColor":"rgba(56, 182, 230, 1)","tableHeaderFontColor":"rgba(0, 1, 4, 1)","inputTitle":"1","tableBtnBorderRadius":"5px","btnAdAllFont":"1","btnAdAllDelFontColor":"rgba(0, 0, 0, 1)","tableBtnIcon":"1","btnAdAllHeight":"46px","btnAdAllWarnBgColor":"rgba(255, 255, 255, 1)","btnAdAllBorderWidth":"5px 000","tableStripeFontColor":"rgba(0, 0, 0, 1)","tableBtnBorderStyle":"double","inputHeight":"42px","btnAdAllBorderRadius":"20px","btnAdAllDelBgColor":"rgba(255, 255, 255, 1)","pagePrevNext":true,"btnAdAllAddBgColor":"rgba(255, 255, 255, 1)","searchBtnFont":"1","tableIndex":true,"btnAdAllIcon":"0","tableSortable":false,"pageSizes":true,"tableFit":true,"pageBtnBG":true,"searchBtnFontSize":"15px","tableBtnEditBgColor":"rgba(56, 182, 230, 1)","box":{"padding":"10px 20px","boxShadow":"0 0 6px rgba(0,0,0,0)","flag":1,"backgroundImage":"http://codegen.caihongy.cn/20211124/229bbb98f09a41bc83995f14c422a0a1.jpg","background":"#fff"},"inputBorderWidth":"5px 000","inputFontPosition":"1","inputFontColor":"rgba(0, 0, 0, 1)","pageEachNum":10,"tableHeaderBgColor":"rgba(119, 197, 227, 1)","inputTitleColor":"rgba(0, 0, 0, 1)","btnAdAllBoxPosition":"1","tableBtnDetailBgColor":"rgba(56, 182, 230, 1)","inputIcon":"1","searchBtnIconPosition":"2","btnAdAllFontSize":"14px","inputBorderStyle":"double","tableHoverFontColor":"#333","inputBgColor":"rgba(255, 255, 255, 1)","pageStyle":false,"pageTotal":true,"btnAdAllAddFontColor":"rgba(0, 0, 0, 1)","tableBtnFont":"1","tableContentFontColor":"rgba(0, 0, 0, 1)","inputBorderColor":"rgba(56, 182, 230, 1)","tableShowHeader":true,"tableHoverBgColor":"rgba(119, 197, 227, 0.8)","tableBtnFontSize":"14px","tableBtnBorderColor":"rgba(255, 255, 255, 1)","inputIconPosition":"2","tableBorder":true,"btnAdAllBorderStyle":"double","tableBtnBorderWidth":"5px","tableStripeBgColor":"rgba(119, 197, 227, 0.8)","tableBtnEditFontColor":"rgba(0, 0, 0, 1)","tableAlign":"center"},
      layouts: '',
      orderStatus: this.$route.params.status,

//导出excel
      json_fields: {
      "订单编号": "orderid",    //常规字段
      "商品表名": "tablename",    //常规字段
      "用户id": "userid",    //常规字段
      "商品id": "goodid",    //常规字段
      "商品名称": "goodname",    //常规字段
      "商品图片": "picture",    //常规字段
      "购买数量": "buynumber",    //常规字段
      "价格": "price",    //常规字段
      "折扣价格": "discountprice",    //常规字段
      "总价格": "total",    //常规字段
      "折扣总价格": "discounttotal",    //常规字段
      "支付类型": "type",    //常规字段
      "状态": "status",    //常规字段
      "地址": "address",    //常规字段
      "电话": "tel",    //常规字段
      "收货人": "consignee",    //常规字段
      "物流": "logistics",    //常规字段
      "商户名称": "maijiazhanghao",    //常规字段
      },
      json_meta: [
        [
          {
            " key ": " charset ",
            " value ": " utf- 8 "
          }
        ]
      ]

    };
  },
  created() {
    this.init();
    this.getDataList();
    this.contentStyleChange()
  },
  mounted() {

  },
//监听订单表参数是否变化，从而调取接口
  watch: {
  '$route' (to, from) { //监听路由是否变化
    if(this.$route.params.status){//判断状态是否有值
      //调数据
        this.orderStatus=this.$route.params.status;
        this.getDataList();
        this.contentStyleChange()
    }
  }
  },
  filters: {
    htmlfilter: function (val) {
      return val.replace(/<[^>]*>/g).replace(/undefined/g,'');
    }
  },
  components: {
    AddOrUpdate,
  },
  methods: {

    orderStatusFormatter: function(row, column) {
      var temp = ''
      // 处理逻辑 保存接口 数据，进行匹配 name
      switch (row.type-0) {
        case 1:
          temp = '现金'
          break
        case 2:
          temp = '积分'
          break
        case 3:
          temp = '拼团'
          break
      }
      return temp
    },
    updateHandler(row) {
      this.$confirm(`确定进行发货操作?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        row.status = "已发货";
        this.$http({
          url: `orders/update`,
          method: "post",
          data: row
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.search();
              }
            });
          } else {
             this.$message.error(data.msg);
          }
        });
      });
    },
    updateHandler2(row) {
      this.$confirm(`确定已收货?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        row.status = "已完成";
        this.$http({
          url: `orders/update`,
          method: "post",
          data: row
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.search();
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        });
      });
    },
    //物流
    logisticsUpdate(id,type) {
      this.showFlag = false;
      this.addOrUpdateFlag = true;
      this.crossAddOrUpdateFlag = false;
      if(type!='info'){
        type = 'logistics';
      }
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id,type);
      });
    },
    contentStyleChange() {
      this.contentSearchStyleChange()
      this.contentBtnAdAllStyleChange()
      this.contentSearchBtnStyleChange()
      this.contentTableBtnStyleChange()
      this.contentPageStyleChange()
    },
    contentSearchStyleChange() {
      this.$nextTick(()=>{
        document.querySelectorAll('.form-content .slt .el-input__inner').forEach(el=>{
          let textAlign = 'left'
          if(this.contents.inputFontPosition == 2) textAlign = 'center'
          if(this.contents.inputFontPosition == 3) textAlign = 'right'
          el.style.textAlign = textAlign
          el.style.height = this.contents.inputHeight
          el.style.lineHeight = this.contents.inputHeight
          el.style.color = this.contents.inputFontColor
          el.style.fontSize = this.contents.inputFontSize
          el.style.borderWidth = this.contents.inputBorderWidth
          el.style.borderStyle = this.contents.inputBorderStyle
          el.style.borderColor = this.contents.inputBorderColor
          el.style.borderRadius = this.contents.inputBorderRadius
          el.style.backgroundColor = this.contents.inputBgColor
        })
        if(this.contents.inputTitle) {
          document.querySelectorAll('.form-content .slt .el-form-item__label').forEach(el=>{
            el.style.color = this.contents.inputTitleColor
            el.style.fontSize = this.contents.inputTitleSize
            el.style.lineHeight = this.contents.inputHeight
          })
        }
        setTimeout(()=>{
          document.querySelectorAll('.form-content .slt .el-input__prefix').forEach(el=>{
            el.style.color = this.contents.inputIconColor
            el.style.lineHeight = this.contents.inputHeight
          })
          document.querySelectorAll('.form-content .slt .el-input__suffix').forEach(el=>{
            el.style.color = this.contents.inputIconColor
            el.style.lineHeight = this.contents.inputHeight
          })
          document.querySelectorAll('.form-content .slt .el-input__icon').forEach(el=>{
            el.style.lineHeight = this.contents.inputHeight
          })
        },10)

      })
    },
    // 搜索按钮
    contentSearchBtnStyleChange() {
      this.$nextTick(()=>{
        document.querySelectorAll('.form-content .slt .el-button--success').forEach(el=>{
          el.style.height = this.contents.searchBtnHeight
          el.style.color = this.contents.searchBtnFontColor
          el.style.fontSize = this.contents.searchBtnFontSize
          el.style.borderWidth = this.contents.searchBtnBorderWidth
          el.style.borderStyle = this.contents.searchBtnBorderStyle
          el.style.borderColor = this.contents.searchBtnBorderColor
          el.style.borderRadius = this.contents.searchBtnBorderRadius
          el.style.backgroundColor = this.contents.searchBtnBgColor
        })
      })
    },
    // 新增、批量删除
    contentBtnAdAllStyleChange() {
      this.$nextTick(()=>{
        document.querySelectorAll('.form-content .ad .el-button--success').forEach(el=>{
          el.style.height = this.contents.btnAdAllHeight
          el.style.color = this.contents.btnAdAllAddFontColor
          el.style.fontSize = this.contents.btnAdAllFontSize
          el.style.borderWidth = this.contents.btnAdAllBorderWidth
          el.style.borderStyle = this.contents.btnAdAllBorderStyle
          el.style.borderColor = this.contents.btnAdAllBorderColor
          el.style.borderRadius = this.contents.btnAdAllBorderRadius
          el.style.backgroundColor = this.contents.btnAdAllAddBgColor
        })
        document.querySelectorAll('.form-content .ad .el-button--danger').forEach(el=>{
          el.style.height = this.contents.btnAdAllHeight
          el.style.color = this.contents.btnAdAllDelFontColor
          el.style.fontSize = this.contents.btnAdAllFontSize
          el.style.borderWidth = this.contents.btnAdAllBorderWidth
          el.style.borderStyle = this.contents.btnAdAllBorderStyle
          el.style.borderColor = this.contents.btnAdAllBorderColor
          el.style.borderRadius = this.contents.btnAdAllBorderRadius
          el.style.backgroundColor = this.contents.btnAdAllDelBgColor
        })
        document.querySelectorAll('.form-content .ad .el-button--warning').forEach(el=>{
          el.style.height = this.contents.btnAdAllHeight
          el.style.color = this.contents.btnAdAllWarnFontColor
          el.style.fontSize = this.contents.btnAdAllFontSize
          el.style.borderWidth = this.contents.btnAdAllBorderWidth
          el.style.borderStyle = this.contents.btnAdAllBorderStyle
          el.style.borderColor = this.contents.btnAdAllBorderColor
          el.style.borderRadius = this.contents.btnAdAllBorderRadius
          el.style.backgroundColor = this.contents.btnAdAllWarnBgColor
        })
      })
    },
    // 表格
    // rowStyle({ row, rowIndex}) {
    //   if (rowIndex % 2 == 1) {
    //     if(this.contents.tableStripe) {
    //       return {color:this.contents.tableStripeFontColor}
    //     }
    //   } else {
    //     return ''
    //   }
    // },
    // cellStyle({ row, rowIndex}){
    //   if (rowIndex % 2 == 1) {
    //     if(this.contents.tableStripe) {
    //       return {backgroundColor:this.contents.tableStripeBgColor}
    //     }
    //   } else {
    //     return ''
    //   }
    // },
    headerRowStyle({ row, rowIndex}){
      return {color: this.contents.tableHeaderFontColor}
    },
    headerCellStyle({ row, rowIndex}){
      return {backgroundColor: this.contents.tableHeaderBgColor}
    },
    // 表格按钮
    contentTableBtnStyleChange(){
      // this.$nextTick(()=>{
      //   setTimeout(()=>{
      //     document.querySelectorAll('.table-content .tables .el-table__body .el-button--success').forEach(el=>{
      //       el.style.height = this.contents.tableBtnHeight
      //       el.style.color = this.contents.tableBtnDetailFontColor
      //       el.style.fontSize = this.contents.tableBtnFontSize
      //       el.style.borderWidth = this.contents.tableBtnBorderWidth
      //       el.style.borderStyle = this.contents.tableBtnBorderStyle
      //       el.style.borderColor = this.contents.tableBtnBorderColor
      //       el.style.borderRadius = this.contents.tableBtnBorderRadius
      //       el.style.backgroundColor = this.contents.tableBtnDetailBgColor
      //     })
      //     document.querySelectorAll('.table-content .tables .el-table__body .el-button--primary').forEach(el=>{
      //       el.style.height = this.contents.tableBtnHeight
      //       el.style.color = this.contents.tableBtnEditFontColor
      //       el.style.fontSize = this.contents.tableBtnFontSize
      //       el.style.borderWidth = this.contents.tableBtnBorderWidth
      //       el.style.borderStyle = this.contents.tableBtnBorderStyle
      //       el.style.borderColor = this.contents.tableBtnBorderColor
      //       el.style.borderRadius = this.contents.tableBtnBorderRadius
      //       el.style.backgroundColor = this.contents.tableBtnEditBgColor
      //     })
      //     document.querySelectorAll('.table-content .tables .el-table__body .el-button--danger').forEach(el=>{
      //       el.style.height = this.contents.tableBtnHeight
      //       el.style.color = this.contents.tableBtnDelFontColor
      //       el.style.fontSize = this.contents.tableBtnFontSize
      //       el.style.borderWidth = this.contents.tableBtnBorderWidth
      //       el.style.borderStyle = this.contents.tableBtnBorderStyle
      //       el.style.borderColor = this.contents.tableBtnBorderColor
      //       el.style.borderRadius = this.contents.tableBtnBorderRadius
      //       el.style.backgroundColor = this.contents.tableBtnDelBgColor
      //     })

      //   }, 50)
      // })
    },
    // 分页
    contentPageStyleChange(){
      let arr = []

      if(this.contents.pageTotal) arr.push('total')
      if(this.contents.pageSizes) arr.push('sizes')
      if(this.contents.pagePrevNext){
        arr.push('prev')
        if(this.contents.pagePager) arr.push('pager')
        arr.push('next')
      }
      if(this.contents.pageJumper) arr.push('jumper')
      this.layouts = arr.join()
      this.contents.pageEachNum = 10
    },

    dayNumberChartDialog() {
      this.dayNumberChartVisiable = !this.dayNumberChartVisiable;
      this.$nextTick(()=>{
        var dayNumberChart = this.$echarts.init(document.getElementById("dayNumberChart"),'macarons');
        this.$http({
            url: `orders/value/addtime/buynumber/日`,
            method: "get"
        }).then(({ data }) => {
            if (data && data.code === 0) {
                let res = data.data;
                let xAxis = [];
                let yAxis = [];
                let pArray = []
                for(let i=0;i<res.length;i++){
                    xAxis.push(res[i].addtime);
                    yAxis.push(parseFloat(res[i].total));
                    pArray.push({
                    value: parseFloat((res[i].total)),
                    name: res[i].addtime
                    })
                    var option = {};
                    option = {
                        tooltip: {
                          trigger: 'item',
                          formatter: '{b} : {c}'
                        },
                        title: {
                            text: '日销量',
                            left: 'center'
                        },
                        xAxis: {
                            type: 'category',
                            data: xAxis
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [{
                            data: yAxis,
                            type: 'bar'
                        }]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    dayNumberChart.setOption(option);
                    //根据窗口的大小变动图表
                    window.onresize = function() {
                        dayNumberChart.resize();
                    };
                }
            }
        });
      })
    },
    
    monthNumberChartDialog() {
      this.monthNumberChartVisiable = !this.monthNumberChartVisiable;
      this.$nextTick(()=>{
        var monthNumberChart = this.$echarts.init(document.getElementById("monthNumberChart"),'macarons');
        this.$http({
            url: `orders/value/addtime/buynumber/月`,
            method: "get"
        }).then(({ data }) => {
            if (data && data.code === 0) {
                let res = data.data;
                let xAxis = [];
                let yAxis = [];
                let pArray = []
                for(let i=0;i<res.length;i++){
                    xAxis.push(res[i].addtime);
                    yAxis.push(parseFloat(res[i].total));
                    pArray.push({
                    value: parseFloat((res[i].total)),
                    name: res[i].addtime
                    })
                    var option = {};
                    option = {
                        tooltip: {
                          trigger: 'item',
                          formatter: '{b} : {c}'
                        },
                        title: {
                            text: '月销量',
                            left: 'center'
                        },
                        xAxis: {
                            type: 'category',
                            data: xAxis
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [{
                            data: yAxis,
                            type: 'bar'
                        }]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    monthNumberChart.setOption(option);
                    //根据窗口的大小变动图表
                    window.onresize = function() {
                        monthNumberChart.resize();
                    };
                }
            }
        });
      })
    },
    
    yearNumberChartDialog() {
      this.yearNumberChartVisiable = !this.yearNumberChartVisiable;
      this.$nextTick(()=>{
        var yearNumberChart = this.$echarts.init(document.getElementById("yearNumberChart"),'macarons');
        this.$http({
            url: `orders/value/addtime/buynumber/年`,
            method: "get"
        }).then(({ data }) => {
            if (data && data.code === 0) {
                let res = data.data;
                let xAxis = [];
                let yAxis = [];
                let pArray = []
                for(let i=0;i<res.length;i++){
                    xAxis.push(res[i].addtime);
                    yAxis.push(parseFloat(res[i].total));
                    pArray.push({
                    value: parseFloat((res[i].total)),
                    name: res[i].addtime
                    })
                    var option = {};
                    option = {
                        tooltip: {
                          trigger: 'item',
                          formatter: '{b} : {c}'
                        },
                        title: {
                            text: '年销量',
                            left: 'center'
                        },
                        xAxis: {
                            type: 'category',
                            data: xAxis
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [{
                            data: yAxis,
                            type: 'bar'
                        }]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    yearNumberChart.setOption(option);
                    //根据窗口的大小变动图表
                    window.onresize = function() {
                        yearNumberChart.resize();
                    };
                }
            }
        });
      })
    },
    
    goodnameNumberChartDialog() {
      this.goodnameNumberChartVisiable = !this.goodnameNumberChartVisiable;
      this.$nextTick(()=>{
        var goodnameNumberChart = this.$echarts.init(document.getElementById("goodnameNumberChart"),'macarons');
        this.$http({
            url: `orders/value/goodname/buynumber`,
            method: "get"
        }).then(({ data }) => {
            if (data && data.code === 0) {
                let res = data.data;
                let xAxis = [];
                let yAxis = [];
                let pArray = []
                for(let i=0;i<res.length;i++){
                    xAxis.push(res[i].goodname);
                    yAxis.push(parseFloat(res[i].total));
                    pArray.push({
                    value: parseFloat((res[i].total)),
                    name: res[i].goodname
                    })
                    var option = {};
                    option = {
                        tooltip: {
                          trigger: 'item',
                          formatter: '{b} : {c}'
                        },
                        title: {
                            text: '商品销量',
                            left: 'center'
                        },
                        xAxis: {
                            type: 'category',
                            data: xAxis
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [{
                            data: yAxis,
                            type: 'bar'
                        }]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    goodnameNumberChart.setOption(option);
                    //根据窗口的大小变动图表
                    window.onresize = function() {
                        goodnameNumberChart.resize();
                    };
                }
            }
        });
      })
    },
    
    goodtypeNumberChartDialog() {
      this.goodtypeNumberChartVisiable = !this.goodtypeNumberChartVisiable;
      this.$nextTick(()=>{
        var goodtypeNumberChart = this.$echarts.init(document.getElementById("goodtypeNumberChart"),'macarons');
        this.$http({
            url: `orders/value/goodtype/buynumber`,
            method: "get"
        }).then(({ data }) => {
            if (data && data.code === 0) {
                let res = data.data;
                let xAxis = [];
                let yAxis = [];
                let pArray = []
                for(let i=0;i<res.length;i++){
                    xAxis.push(res[i].goodtype);
                    yAxis.push(parseFloat(res[i].total));
                    pArray.push({
                    value: parseFloat((res[i].total)),
                    name: res[i].goodtype
                    })
                    var option = {};
                    option = {
                        tooltip: {
                          trigger: 'item',
                          formatter: '{b} : {c}'
                        },
                        title: {
                            text: '类型销量',
                            left: 'center'
                        },
                        xAxis: {
                            type: 'category',
                            data: xAxis
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [{
                            data: yAxis,
                            type: 'bar'
                        }]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    goodtypeNumberChart.setOption(option);
                    //根据窗口的大小变动图表
                    window.onresize = function() {
                        goodtypeNumberChart.resize();
                    };
                }
            }
        });
      })
    },
    
    dayAmountChartDialog() {
      this.dayAmountChartVisiable = !this.dayAmountChartVisiable;
      this.$nextTick(()=>{
        var dayAmountChart = this.$echarts.init(document.getElementById("dayAmountChart"),'macarons');
        this.$http({
            url: `orders/value/addtime/total/日`,
            method: "get"
        }).then(({ data }) => {
            if (data && data.code === 0) {
                let res = data.data;
                let xAxis = [];
                let yAxis = [];
                let pArray = []
                for(let i=0;i<res.length;i++){
                    xAxis.push(res[i].addtime);
                    yAxis.push(parseFloat((res[i].total)).toFixed(2));
                    pArray.push({
                    value: parseFloat((res[i].total)),
                    name: res[i].addtime
                    })
                    var option = {};
                    option = {
                        tooltip: {
                          trigger: 'item',
                          formatter: '{b} : {c}'
                        },
                        title: {
                            text: '日销额',
                            left: 'center'
                        },
                        xAxis: {
                            type: 'category',
                            data: xAxis
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [{
                            data: yAxis,
                            type: 'bar'
                        }]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    dayAmountChart.setOption(option);
                    //根据窗口的大小变动图表
                    window.onresize = function() {
                        dayAmountChart.resize();
                    };
                }
            }
        });
      })
    },
    
    monthAmountChartDialog() {
      this.monthAmountChartVisiable = !this.monthAmountChartVisiable;
      this.$nextTick(()=>{
        var monthAmountChart = this.$echarts.init(document.getElementById("monthAmountChart"),'macarons');
        this.$http({
            url: `orders/value/addtime/total/月`,
            method: "get"
        }).then(({ data }) => {
            if (data && data.code === 0) {
                let res = data.data;
                let xAxis = [];
                let yAxis = [];
                let pArray = []
                for(let i=0;i<res.length;i++){
                    xAxis.push(res[i].addtime);
                    yAxis.push(parseFloat((res[i].total)).toFixed(2));
                    pArray.push({
                    value: parseFloat((res[i].total)),
                    name: res[i].addtime
                    })
                    var option = {};
                    option = {
                        tooltip: {
                          trigger: 'item',
                          formatter: '{b} : {c}'
                        },
                        title: {
                            text: '月销额',
                            left: 'center'
                        },
                        xAxis: {
                            type: 'category',
                            data: xAxis
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [{
                            data: yAxis,
                            type: 'bar'
                        }]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    monthAmountChart.setOption(option);
                    //根据窗口的大小变动图表
                    window.onresize = function() {
                        monthAmountChart.resize();
                    };
                }
            }
        });
      })
    },
    
    yearAmountChartDialog() {
      this.yearAmountChartVisiable = !this.yearAmountChartVisiable;
      this.$nextTick(()=>{
        var yearAmountChart = this.$echarts.init(document.getElementById("yearAmountChart"),'macarons');
        this.$http({
            url: `orders/value/addtime/total/年`,
            method: "get"
        }).then(({ data }) => {
            if (data && data.code === 0) {
                let res = data.data;
                let xAxis = [];
                let yAxis = [];
                let pArray = []
                for(let i=0;i<res.length;i++){
                    xAxis.push(res[i].addtime);
                    yAxis.push(parseFloat((res[i].total)).toFixed(2));
                    pArray.push({
                    value: parseFloat((res[i].total)),
                    name: res[i].addtime
                    })
                    var option = {};
                    option = {
                        tooltip: {
                          trigger: 'item',
                          formatter: '{b} : {c}'
                        },
                        title: {
                            text: '年销额',
                            left: 'center'
                        },
                        xAxis: {
                            type: 'category',
                            data: xAxis
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [{
                            data: yAxis,
                            type: 'bar'
                        }]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    yearAmountChart.setOption(option);
                    //根据窗口的大小变动图表
                    window.onresize = function() {
                        yearAmountChart.resize();
                    };
                }
            }
        });
      })
    },
    
    goodnameAmountChartDialog() {
      this.goodnameAmountChartVisiable = !this.goodnameAmountChartVisiable;
      this.$nextTick(()=>{
        var goodnameAmountChart = this.$echarts.init(document.getElementById("goodnameAmountChart"),'macarons');
        this.$http({
            url: `orders/value/goodname/total`,
            method: "get"
        }).then(({ data }) => {
            if (data && data.code === 0) {
                let res = data.data;
                let xAxis = [];
                let yAxis = [];
                let pArray = []
                for(let i=0;i<res.length;i++){
                    xAxis.push(res[i].goodname);
                    yAxis.push(parseFloat((res[i].total)).toFixed(2));
                    pArray.push({
                    value: parseFloat((res[i].total)),
                    name: res[i].goodname
                    })
                    var option = {};
                    option = {
                        tooltip: {
                          trigger: 'item',
                          formatter: '{b} : {c}'
                        },
                        title: {
                            text: '商品销额',
                            left: 'center'
                        },
                        xAxis: {
                            type: 'category',
                            data: xAxis
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [{
                            data: yAxis,
                            type: 'bar'
                        }]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    goodnameAmountChart.setOption(option);
                    //根据窗口的大小变动图表
                    window.onresize = function() {
                        goodnameAmountChart.resize();
                    };
                }
            }
        });
      })
    },
    
    goodtypeAmountChartDialog() {
      this.goodtypeAmountChartVisiable = !this.goodtypeAmountChartVisiable;
      this.$nextTick(()=>{
        var goodtypeAmountChart = this.$echarts.init(document.getElementById("goodtypeAmountChart"),'macarons');
        this.$http({
            url: `orders/value/goodtype/total`,
            method: "get"
        }).then(({ data }) => {
            if (data && data.code === 0) {
                let res = data.data;
                let xAxis = [];
                let yAxis = [];
                let pArray = []
                for(let i=0;i<res.length;i++){
                    xAxis.push(res[i].goodtype);
                    yAxis.push(parseFloat((res[i].total)).toFixed(2));
                    pArray.push({
                    value: parseFloat((res[i].total)),
                    name: res[i].goodtype
                    })
                    var option = {};
                    option = {
                        tooltip: {
                          trigger: 'item',
                          formatter: '{b} : {c}'
                        },
                        title: {
                            text: '类型销额',
                            left: 'center'
                        },
                        xAxis: {
                            type: 'category',
                            data: xAxis
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [{
                            data: yAxis,
                            type: 'bar'
                        }]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    goodtypeAmountChart.setOption(option);
                    //根据窗口的大小变动图表
                    window.onresize = function() {
                        goodtypeAmountChart.resize();
                    };
                }
            }
        });
      })
    },
    init () {
    },
    search() {
      this.pageIndex = 1;
      this.getDataList();
    },

    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      let params = {
        page: this.pageIndex,
        limit: this.pageSize,
        sort: 'id',
	status: this.$route.params.status,
      }
          if(this.searchForm.orderid!='' && this.searchForm.orderid!=undefined){
            params['orderid'] = '%' + this.searchForm.orderid + '%'
          }
          if(this.searchForm.goodname!='' && this.searchForm.goodname!=undefined){
            params['goodname'] = '%' + this.searchForm.goodname + '%'
          }
      this.$http({
        url: "orders/page",
        method: "get",
        params: params
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.data.list;
          this.totalPage = data.data.total;
        } else {
          this.dataList = [];
          this.totalPage = 0;
        }
        this.dataListLoading = false;
      });
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    // 多选
    selectionChangeHandler(val) {
      this.dataListSelections = val;
    },
    // 添加/修改
    addOrUpdateHandler(id,type) {
      this.showFlag = false;
      this.addOrUpdateFlag = true;
      this.crossAddOrUpdateFlag = false;
      if(type!='info'){
        type = 'else';
      }
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id,type);
      });
    },
    // 查看评论
    // 下载
    download(file){
      window.open(`${file}`)
    },
    // 删除
    deleteHandler(id) {
      var ids = id
        ? [Number(id)]
        : this.dataListSelections.map(item => {
            return Number(item.id);
          });
      this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$http({
          url: "orders/delete",
          method: "post",
          data: ids
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.search();
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        });
      });
    },


  }

};
</script>
<style lang="scss" scoped>
//导出excel
  .export-excel-wrapper{
    display: inline-block;
  }
  .slt {
    margin: 0 !important;
    display: flex;
  }

  .ad {
    margin: 0 !important;
    display: flex;
  }

  .pages {
    & /deep/ el-pagination__sizes{
      & /deep/ el-input__inner {
        height: 22px;
        line-height: 22px;
      }
    }
  }
  

  .el-button+.el-button {
    margin:0;
  } 

  .tables {
	& /deep/ .el-button--success {
		height: 40px;
		color: rgba(0, 0, 0, 1);
		font-size: 14px;
		border-width: 5px;
		border-style: double;
		border-color: rgba(255, 255, 255, 1);
		border-radius: 5px;
		background-color: rgba(56, 182, 230, 1);
	}
	
	& /deep/ .el-button--primary {
		height: 40px;
		color: rgba(0, 0, 0, 1);
		font-size: 14px;
		border-width: 5px;
		border-style: double;
		border-color: rgba(255, 255, 255, 1);
		border-radius: 5px;
		background-color: rgba(56, 182, 230, 1);
	}
	
	& /deep/ .el-button--danger {
		height: 40px;
		color: rgba(0, 0, 0, 1);
		font-size: 14px;
		border-width: 5px;
		border-style: double;
		border-color: rgba(255, 255, 255, 1);
		border-radius: 5px;
		background-color: rgba(56, 182, 230, 1);
	}

    & /deep/ .el-button {
      margin: 4px;
    }
  }
	.form-content {
		background: transparent;
	}
	.table-content {
		background: transparent;
	}
	
	.tables /deep/ .el-table__body tr {
				background-color: rgba(255, 255, 255, 1) !important;
				color: rgba(0, 0, 0, 1) !important;
	 }
	.tables /deep/ .el-table__body tr.el-table__row--striped td {
	    background: transparent;
	}
	.tables /deep/ .el-table__body tr.el-table__row--striped {
		background-color: rgba(119, 197, 227, 0.8) !important;
		color: rgba(0, 0, 0, 1) !important;
	}
	
	 .tables /deep/ .el-table__body tr:hover>td {
	   	   background-color: rgba(119, 197, 227, 0.8) !important;
	   	   	   color: #333 !important;
	   	 }
	 
</style>
