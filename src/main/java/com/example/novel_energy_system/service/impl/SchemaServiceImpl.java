package com.example.novel_energy_system.service.impl;

import com.example.novel_energy_system.service.SchemaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SchemaServiceImpl implements SchemaService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String getElecline() throws JsonProcessingException {
        // 创建 Elecline 的子字段
        Map<String, Object> resistance = new HashMap<>();
        resistance.put("type", "string");
        resistance.put("unit", new String[]{"ohm", "kohm"});

        Map<String, Object> impedance = new HashMap<>();
        impedance.put("type", "string");
        impedance.put("unit", new String[]{"ohm", "kohm"});

        Map<String, Object> Vmin = new HashMap<>();
        Vmin.put("type", "string");
        Vmin.put("unit", "p.u.");

        Map<String, Object> Vmax = new HashMap<>();
        Vmax.put("type", "string");
        Vmax.put("unit", "p.u.");

        Map<String, Object> Anglemin = new HashMap<>();
        Anglemin.put("type", "string");
        Anglemin.put("unit", "-Π/3");

        Map<String, Object> Anglemax = new HashMap<>();
        Anglemax.put("type", "string");
        Anglemax.put("unit", "Π/3");

        // 创建 Elecline 字段
        Map<String, Object> ELecline = new HashMap<>();
        ELecline.put("电阻", resistance);
        ELecline.put("阻抗", impedance);
        ELecline.put("电压最小值",Vmin);
        ELecline.put("电压最大值",Vmax);
        ELecline.put("相角最小值",Anglemin);
        ELecline.put("相角最大值",Anglemax);

        return objectMapper.writeValueAsString(ELecline);
    }

    @Override
    public String getALK() throws JsonProcessingException {
        // 创建 ALK 的子字段
        Map<String, Object> G = new HashMap<>();
        G.put("type", "string");
        G.put("unit", "J/mol");

        Map<String, Object> z = new HashMap<>();
        z.put("type", "integer");
        z.put("unit", "");

        Map<String, Object> F = new HashMap<>();
        F.put("type", "string");
        F.put("unit", "C/mol");

        Map<String, Object> r1 = new HashMap<>();
        r1.put("type", "string");
        r1.put("unit", "Omega*m^2");

        Map<String, Object> r2 = new HashMap<>();
        r2.put("type", "string");
        r2.put("unit", "Omega*m^2^oC");

        Map<String, Object> A = new HashMap<>();
        A.put("type", "string");
        A.put("unit", "m^2");

        Map<String, Object> s = new HashMap<>();
        s.put("type", "string");
        s.put("unit", "V");

        Map<String, Object> t1 = new HashMap<>();
        t1.put("type", "string");
        t1.put("unit", "m^2/A");

        Map<String, Object> t2 = new HashMap<>();
        t2.put("type", "string");
        t2.put("unit", "m^2/A*^oC");

        Map<String, Object> t3 = new HashMap<>();
        t3.put("type", "string");
        t3.put("unit", "m^2/A*^oC^2");

        Map<String, Object> N = new HashMap<>();
        N.put("type", "string");
        N.put("unit", "");

        Map<String, Object> f1 = new HashMap<>();
        f1.put("type", "string");
        f1.put("unit", "");

        Map<String, Object> f2 = new HashMap<>();
        f2.put("type", "string");
        f2.put("unit", "m^2/A*^oC^2");

        Map<String, Object> Vth = new HashMap<>();
        Vth.put("type", "string");
        Vth.put("unit", "V");

        Map<String, Object> h = new HashMap<>();
        h.put("type", "string");
        h.put("unit", "m^2/A*^oC^2");

        Map<String, Object> Astack = new HashMap<>();
        Astack.put("type", "string");
        Astack.put("unit", "m^2/A*^oC^2");

        // 创建 ALK 字段
        Map<String, Object> ALK = new HashMap<>();
        ALK.put("吉布斯自由能变化量", G);
        ALK.put("转移电子系数", z);
        ALK.put("法拉第常数", F);
        ALK.put("欧姆过电位相关系数r1", r1);
        ALK.put("欧姆过电位相关系数r2", r2);
        ALK.put("电极板面积", A);
        ALK.put("激活过电位相关系数s", s);
        ALK.put("激活过电位相关系数t1", t1);
        ALK.put("激活过电位相关系数t2", t2);
        ALK.put("激活过电位相关系数t3", t3);
        ALK.put("电解槽个数", N);
        ALK.put("法拉第效率相关系数f1", f1);
        ALK.put("法拉第效率相关系数f2", f2);
        ALK.put("热中性电压", Vth);
        ALK.put("与环境的传热系数", h);
        ALK.put("换热面积", Astack);

        return objectMapper.writeValueAsString(ALK);
    }

    @Override
    public String getPEM() throws JsonProcessingException {
        // 创建 PEM 的子字段
        Map<String, Object> T_std = new HashMap<>();
        T_std.put("type", "string");
        T_std.put("unit", "K");

        Map<String, Object> P_an = new HashMap<>();
        P_an.put("type", "integer");
        P_an.put("unit", "atm");

        Map<String, Object> P_cat = new HashMap<>();
        P_cat.put("type", "string");
        P_cat.put("unit", "atm");

        Map<String, Object> A = new HashMap<>();
        A.put("type", "string");
        A.put("unit", "cm^2");

        Map<String, Object> i0 = new HashMap<>();
        i0.put("type", "string");
        i0.put("unit", "A/cm^2");

        Map<String, Object> E_exc = new HashMap<>();
        E_exc.put("type", "string");
        E_exc.put("unit", "J/mol");

        Map<String, Object> alpha = new HashMap<>();
        alpha.put("type", "string");
        alpha.put("unit", "A/cm^2");

        Map<String, Object> theta = new HashMap<>();
        theta.put("type", "string");
        theta.put("unit", "S/cm");

        Map<String, Object> E_pro = new HashMap<>();
        E_pro.put("type", "string");
        E_pro.put("unit", "J/mol");

        Map<String, Object> thick = new HashMap<>();
        thick.put("type", "string");
        thick.put("unit", "cm");

        Map<String, Object> N_cell = new HashMap<>();
        N_cell.put("type", "string");
        N_cell.put("unit", "");

        Map<String, Object> W = new HashMap<>();
        W.put("type", "string");
        W.put("unit", "kW");

        Map<String, Object> kexi = new HashMap<>();
        kexi.put("type", "string");
        kexi.put("unit", "");

        Map<String, Object> Vm = new HashMap<>();
        Vm.put("type", "string");
        Vm.put("unit", "L/min");

        Map<String, Object> dP = new HashMap<>();
        dP.put("type", "string");
        dP.put("unit", "bar");

        Map<String, Object> Rth = new HashMap<>();
        Rth.put("type", "string");
        Rth.put("unit", "K/W");

        Map<String, Object> CT = new HashMap<>();
        CT.put("type", "string");
        CT.put("unit", "kJ/K");

        // 创建 PEM 字段
        Map<String, Object> PEM = new HashMap<>();
        PEM.put("标准温度", T_std);
        PEM.put("阳极压力", P_an);
        PEM.put("阴极压力", P_cat);
        PEM.put("电极面积", A);
        PEM.put("参考压力和温度下的交换电流密度", i0);
        PEM.put("阳极中电子传输所需的活化能", E_exc);
        PEM.put("阳极电荷转移系数", alpha);
        PEM.put("参考温度和压力下的膜电导率", theta);
        PEM.put("质子在膜中传输所需的活化能", E_pro);
        PEM.put("PEM的厚度", thick);
        PEM.put("电解槽个数", N_cell);
        PEM.put("泵的功率", W);
        PEM.put("泵的效率", kexi);
        PEM.put("水流量", Vm);
        PEM.put("水泵压力", dP);
        PEM.put("热阻", Rth);
        PEM.put("比热容", CT);

        return objectMapper.writeValueAsString(PEM);
    }

    @Override
    public String getPEMFC() throws JsonProcessingException {
        // 创建 PEMFC 的子字段
        Map<String, Object> P_H2 = new HashMap<>();
        P_H2.put("type", "string");
        P_H2.put("unit", "K");

        Map<String, Object> P_O2 = new HashMap<>();
        P_O2.put("type", "integer");
        P_O2.put("unit", "atm");

        Map<String, Object> P_H2O = new HashMap<>();
        P_H2O.put("type", "string");
        P_H2O.put("unit", "atm");

        Map<String, Object> alpha = new HashMap<>();
        alpha.put("type", "string");
        alpha.put("unit", "cm^2");

        Map<String, Object> A = new HashMap<>();
        A.put("type", "string");
        A.put("unit", "cm^2");

        Map<String, Object> ka = new HashMap<>();
        ka.put("type", "string");
        ka.put("unit", "A/m^2");

        Map<String, Object> kc = new HashMap<>();
        kc.put("type", "string");
        kc.put("unit", "A/m^2");

        Map<String, Object> rp = new HashMap<>();
        rp.put("type", "string");
        rp.put("unit", "m");

        Map<String, Object> o = new HashMap<>();
        o.put("type", "string");
        o.put("unit", "");

        Map<String, Object> T_std = new HashMap<>();
        T_std.put("type", "string");
        T_std.put("unit", "K");

        Map<String, Object> Ea = new HashMap<>();
        Ea.put("type", "string");
        Ea.put("unit", "J/mol");

        Map<String, Object> Ec = new HashMap<>();
        Ec.put("type", "string");
        Ec.put("unit", "J/mol");

        Map<String, Object> thetaYSZ = new HashMap<>();
        thetaYSZ.put("type", "string");
        thetaYSZ.put("unit", "m");

        Map<String, Object> thetaLSM = new HashMap<>();
        thetaLSM.put("type", "string");
        thetaLSM.put("unit", "m");

        Map<String, Object> thetaNIYSZ = new HashMap<>();
        thetaNIYSZ.put("type", "string");
        thetaNIYSZ.put("unit", "m");

        Map<String, Object> h = new HashMap<>();
        h.put("type", "string");
        h.put("unit", "W/(m^2*K");

        Map<String, Object> N_cell = new HashMap<>();
        N_cell.put("type", "string");
        N_cell.put("unit", "");

        Map<String, Object> CrSOC = new HashMap<>();
        CrSOC.put("type", "string");
        CrSOC.put("unit", "J/(kg*K)");

        // 创建 PEMFC 字段
        Map<String, Object> PEMFC = new HashMap<>();
        PEMFC.put("氢气分压", P_H2);
        PEMFC.put("氧气分压", P_O2);
        PEMFC.put("水蒸气分压", P_H2O);
        PEMFC.put("传递系数", alpha);
        PEMFC.put("电极面积", A);
        PEMFC.put("阳极反应速率常数", ka);
        PEMFC.put("阴极反应速率常数", kc);
        PEMFC.put("颗粒半径",rp);
        PEMFC.put("孔隙率", o);
        PEMFC.put("标准温度", T_std);
        PEMFC.put("阳极活化能", Ea);
        PEMFC.put("阴极活化能", Ec);
        PEMFC.put("YSZ层厚度", thetaYSZ);
        PEMFC.put("LSM层厚度", thetaLSM);
        PEMFC.put("Ni-YSZ层厚度", thetaNIYSZ);
        PEMFC.put("传热系数", h);
        PEMFC.put("电池单元数", N_cell);
        PEMFC.put("燃料电池热容", CrSOC);

        return objectMapper.writeValueAsString(PEMFC);
    }

    @Override
    public String getWIND() throws JsonProcessingException {
        // 创建 WIND 的子字段
        Map<String, Object> rotor = new HashMap<>();
        rotor.put("type", "string");
        rotor.put("unit", "m");

        Map<String, Object> hub = new HashMap<>();
        hub.put("type", "string");
        hub.put("unit", "m");

        Map<String, Object> Ct = new HashMap<>();
        Ct.put("type", "string");
        Ct.put("unit", "");

        Map<String, Object> wake = new HashMap<>();
        wake.put("type", "string");
        wake.put("unit", "");

        Map<String, Object> cut_in = new HashMap<>();
        cut_in.put("type", "string");
        cut_in.put("unit", "m/s");

        Map<String, Object> V_rated = new HashMap<>();
        V_rated.put("type", "string");
        V_rated.put("unit", "m/s");

        Map<String, Object> cut_out = new HashMap<>();
        cut_out.put("type", "string");
        cut_out.put("unit", "m/s");

        Map<String, Object> P_rated = new HashMap<>();
        P_rated.put("type", "string");
        P_rated.put("unit", "kW");

        Map<String, Object> num = new HashMap<>();
        num.put("type", "string");
        num.put("unit", "");

        Map<String, Object> location = new HashMap<>();
        location.put("type", "string");
        location.put("unit", "(x,y)m");

        // 创建 WIND 字段
        Map<String, Object> WIND = new HashMap<>();
        WIND.put("转子直径", rotor);
        WIND.put("轮毂高度", hub);
        WIND.put("推力系数", Ct);
        WIND.put("尾流衰减系数", wake);
        WIND.put("切入风速", cut_in);
        WIND.put("额定风速", V_rated);
        WIND.put("切出风速", cut_out);
        WIND.put("额定功率", P_rated);
        WIND.put("风机数量", num);
        WIND.put("风机坐标", location);

        return objectMapper.writeValueAsString(WIND);
    }

    @Override
    public String getPV() throws JsonProcessingException {
        // 创建 PV 的子字段
        Map<String, Object> a = new HashMap<>();
        a.put("type", "string");
        a.put("unit", "");

        Map<String, Object> b = new HashMap<>();
        b.put("type", "string");
        b.put("unit", "");

        Map<String, Object> g = new HashMap<>();
        g.put("type", "string");
        g.put("unit", "");

        Map<String, Object> N_cell = new HashMap<>();
        N_cell.put("type", "string");
        N_cell.put("unit", "");

        Map<String, Object> N_series = new HashMap<>();
        N_series.put("type", "string");
        N_series.put("unit", "");

        Map<String, Object> I_sc = new HashMap<>();
        I_sc.put("type", "string");
        I_sc.put("unit", "A");

        Map<String, Object> U_oc = new HashMap<>();
        U_oc.put("type", "string");
        U_oc.put("unit", "V");

        Map<String, Object> I_m = new HashMap<>();
        I_m.put("type", "string");
        I_m.put("unit", "A");

        Map<String, Object> U_m = new HashMap<>();
        U_m.put("type", "string");
        U_m.put("unit", "V");


        // 创建 PV 字段
        Map<String, Object> PV = new HashMap<>();
        PV.put("超参数a", a);
        PV.put("超参数b", b);
        PV.put("超参数g", g);
        PV.put("单组串并联电池数Ncell", N_cell);
        PV.put("串联组串数Nseries", N_series);
        PV.put("短路电流Isc", I_sc);
        PV.put("开路电压Uoc", U_oc);
        PV.put("标准条件下最大功率点电流Im", I_m);
        PV.put("标准条件下最大功率点电压Um", U_m);


        return objectMapper.writeValueAsString(PV);
    }

    @Override
    public String getSolarTB() throws JsonProcessingException {
        // 创建 SolarTB 的子字段
        Map<String, Object> l = new HashMap<>();
        l.put("type", "string");
        l.put("unit", "°");

        Map<String, Object> f = new HashMap<>();
        f.put("type", "string");
        f.put("unit", "°");

        Map<String, Object> tz = new HashMap<>();
        tz.put("type", "string");
        tz.put("unit", "");

        Map<String, Object> N_helio = new HashMap<>();
        N_helio.put("type", "string");
        N_helio.put("unit", "");

        Map<String, Object> d_helio = new HashMap<>();
        d_helio.put("type", "string");
        d_helio.put("unit", "m");

        Map<String, Object> A = new HashMap<>();
        A.put("type", "string");
        A.put("unit", "m^2");

        Map<String, Object> h_clean = new HashMap<>();
        h_clean.put("type", "string");
        h_clean.put("unit", "");

        Map<String, Object> h_reflect = new HashMap<>();
        h_reflect.put("type", "string");
        h_reflect.put("unit", "A");

        Map<String, Object> h_STB = new HashMap<>();
        h_STB.put("type", "string");
        h_STB.put("unit", "m");

        Map<String, Object> N_pipe = new HashMap<>();
        N_pipe.put("type", "string");
        N_pipe.put("unit", "m");

        Map<String, Object> L_pipe = new HashMap<>();
        L_pipe.put("type", "string");
        L_pipe.put("unit", "m");

        Map<String, Object> d_out = new HashMap<>();
        d_out.put("type", "string");
        d_out.put("unit", "m");

        Map<String, Object> d_thick = new HashMap<>();
        d_thick.put("type", "string");
        d_thick.put("unit", "m");

        Map<String, Object> e = new HashMap<>();
        e.put("type", "string");
        e.put("unit", "m");

        Map<String, Object> h_absorb = new HashMap<>();
        h_absorb.put("type", "string");
        h_absorb.put("unit", "m");

        Map<String, Object> r_pipe = new HashMap<>();
        r_pipe.put("type", "string");
        r_pipe.put("unit", "m");

        Map<String, Object> C_pipe = new HashMap<>();
        C_pipe.put("type", "string");
        C_pipe.put("unit", "m");



        // 创建 SolarTB 字段
        Map<String, Object> SolarTB = new HashMap<>();
        SolarTB.put("光热镜场经度", l);
        SolarTB.put("光热镜场纬度", f);
        SolarTB.put("光热镜场时区", tz);
        SolarTB.put("定日镜个数", N_helio);
        SolarTB.put("定日镜间距", d_helio);
        SolarTB.put("定日镜面积", A);
        SolarTB.put("定日镜清洁度系数", h_clean);
        SolarTB.put("定日镜反射效率", h_reflect);
        SolarTB.put("光热锅炉高度", h_STB);
        SolarTB.put("换热管数量", N_pipe);
        SolarTB.put("换热管长度", L_pipe);
        SolarTB.put("换热管外径", d_out);
        SolarTB.put("换热管管壁厚度", d_thick);
        SolarTB.put("换热管外壁黑度", e);
        SolarTB.put("换热管外壁吸热效率", h_absorb);
        SolarTB.put("换热管金属密度", r_pipe);
        SolarTB.put("换热管金属比热容", C_pipe);


        return objectMapper.writeValueAsString(SolarTB);
    }

    @Override
    public String getMolten_salt_steam_generator() throws JsonProcessingException {
        // 创建 SolarTB 的子字段
        Map<String, Object> q = new HashMap<>();
        q.put("type", "string");
        q.put("unit", "kg/s");

        Map<String, Object> h_in = new HashMap<>();
        h_in.put("type", "string");
        h_in.put("unit", "kJ/kg");

        Map<String, Object> h_out = new HashMap<>();
        h_out.put("type", "string");
        h_out.put("unit", "kJ/kg");
        // 创建 SolarTB 字段
        Map<String, Object> Molten_salt_steam_generator = new HashMap<>();
        Molten_salt_steam_generator.put("光热镜场经度", q);
        Molten_salt_steam_generator.put("光热镜场纬度", h_in);
        Molten_salt_steam_generator.put("光热镜场时区", h_out);
        return objectMapper.writeValueAsString(Molten_salt_steam_generator);
    }

    @Override
    public String getMolten_salt_heat_storage() throws JsonProcessingException {
        // 创建 getMolten_salt_heat_storage 的子字段
        Map<String, Object> DT = new HashMap<>();
        DT.put("type", "string");
        DT.put("unit", "m");

        Map<String, Object> Molten_salt_heat_storage = new HashMap<>();
        Molten_salt_heat_storage.put("储罐的直径", DT);
        return objectMapper.writeValueAsString(Molten_salt_heat_storage);
    }

    @Override
    public String getLava_heat_storage() throws JsonProcessingException {
        // 创建 Lava_heat_storage 的子字段
        Map<String, Object> kexi = new HashMap<>();
        kexi.put("type", "string");
        kexi.put("unit", "");

        Map<String, Object> q_loss = new HashMap<>();
        q_loss.put("type", "string");
        q_loss.put("unit", "kJ/s");

        Map<String, Object> Lava_heat_storage = new HashMap<>();
        Lava_heat_storage.put("锅炉效率", kexi);
        Lava_heat_storage.put("热损失", q_loss);
        return objectMapper.writeValueAsString(Lava_heat_storage);
    }

    @Override
    public String getSteam_turbine() throws JsonProcessingException {
        // 创建 Steam_turbine 的子字段
        Map<String, Object> T_CH = new HashMap<>();
        T_CH.put("type", "string");
        T_CH.put("unit", "");

        Map<String, Object> T_RH = new HashMap<>();
        T_RH.put("type", "string");
        T_RH.put("unit", "");

        Map<String, Object> T_CO = new HashMap<>();
        T_CO.put("type", "string");
        T_CO.put("unit", "");

        Map<String, Object> F_HP = new HashMap<>();
        F_HP.put("type", "string");
        F_HP.put("unit", "");

        Map<String, Object> F_IP = new HashMap<>();
        F_IP.put("type", "string");
        F_IP.put("unit", "");

        Map<String, Object> F_LP = new HashMap<>();
        F_LP.put("type", "string");
        F_LP.put("unit", "A");

        Map<String, Object> K_p = new HashMap<>();
        K_p.put("type", "string");
        K_p.put("unit", "V");

        Map<String, Object> T_p = new HashMap<>();
        T_p.put("type", "string");
        T_p.put("unit", "A");


        // 创建 Steam_turbine 字段
        Map<String, Object> Steam_turbine = new HashMap<>();
        Steam_turbine.put("超参数a", T_CH);
        Steam_turbine.put("超参数b", T_RH);
        Steam_turbine.put("超参数g", T_CO);
        Steam_turbine.put("单组串并联电池数Ncell", F_HP);
        Steam_turbine.put("串联组串数Nseries", F_IP);
        Steam_turbine.put("短路电流Isc", F_LP);
        Steam_turbine.put("开路电压Uoc", K_p);
        Steam_turbine.put("标准条件下最大功率点电流Im", T_p);

        return objectMapper.writeValueAsString(Steam_turbine);
    }

    @Override
    public String getRefinery() throws JsonProcessingException {
        // 创建 Refinery 的子字段
        Map<String, Object> ele = new HashMap<>();
        ele.put("type", "string");
        ele.put("unit", "Wh/t");

        Map<String, Object> h2 = new HashMap<>();
        h2.put("type", "string");
        h2.put("unit", "t/t");

        Map<String, Object> gas = new HashMap<>();
        gas.put("type", "string");
        gas.put("unit", "t/t");

        Map<String, Object> steam1 = new HashMap<>();
        steam1.put("type", "string");
        steam1.put("unit", "t/t");

        Map<String, Object> steam2 = new HashMap<>();
        steam2.put("type", "string");
        steam2.put("unit", "t/t");

        Map<String, Object> steam3 = new HashMap<>();
        steam3.put("type", "string");
        steam3.put("unit", "t/t");

        // 创建 Steam_turbine 字段
        Map<String, Object> Refinery = new HashMap<>();
        Refinery.put("每单位消耗电的量", ele);
        Refinery.put("每单位消耗氢的量", h2);
        Refinery.put("每单位消耗天然气的量", gas);
        Refinery.put("每单位消耗低压蒸汽的量", steam1);
        Refinery.put("每单位消耗中压蒸汽的量", steam2);
        Refinery.put("每单位消耗高压蒸汽的量", steam3);

        return objectMapper.writeValueAsString(Refinery);
    }

    @Override
    public String geth2_gas() throws JsonProcessingException {
        // 创建 h2_gas 的子字段
        Map<String, Object> l = new HashMap<>();
        l.put("type", "string");
        l.put("unit", "Km");

        Map<String, Object> d = new HashMap<>();
        d.put("type", "string");
        d.put("unit", "mm");

        Map<String, Object> a = new HashMap<>();
        a.put("type", "string");
        a.put("unit", "");

        // 创建 h2_gas 字段
        Map<String, Object> h2_gas = new HashMap<>();
        h2_gas.put("管道长度", l);
        h2_gas.put("管道直径", d);
        h2_gas.put("管道气体传输系数", a);

        return objectMapper.writeValueAsString(h2_gas);
    }
}
