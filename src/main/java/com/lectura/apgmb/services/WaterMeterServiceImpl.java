package com.lectura.apgmb.services;

import com.lectura.apgmb.dtos.meter.MeterResponse;
import com.lectura.apgmb.dtos.meter.UpdateMeterRequest;
import com.lectura.apgmb.dtos.meter.UpdateMeterResponse;
import com.lectura.apgmb.entities.WaterMeter;
import com.lectura.apgmb.exceptions.ObjectNotFoundException;
import com.lectura.apgmb.repositories.WaterMeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WaterMeterServiceImpl implements WaterMeterService{
    @Autowired
    private WaterMeterRepository meterRepository;
    @Override
    public WaterMeter createMeter(WaterMeter meter) {
        WaterMeter newMeter = new WaterMeter();
        newMeter.setMeterMark(meter.getMeterMark());
        newMeter.setMeterNumber(meter.getMeterNumber());
        newMeter.setActive(true);
        newMeter.setCreateAt(new Date());
        try {
            meterRepository.save(newMeter);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return newMeter;
    }

    @Override
    public List<MeterResponse> getAllMeters() {
        return meterRepository.findAll().stream().map( e -> new MeterResponse(
            e.getMeterId(),
                e.getCreateAt(),
                e.isActive(),
                e.getMeterMark(),
                e.getMeterNumber()
        )).collect(Collectors.toList());
    }

    @Override
    public Optional<WaterMeter> findById(Long idMeter) {
        return meterRepository.findById(idMeter).stream().findFirst();
    }

    @Override
    public UpdateMeterResponse updateMeter(UpdateMeterRequest updateMeterRequest) {
        System.out.println("updateMeterRequest" + updateMeterRequest.getMarkMeter());
        WaterMeter meterFound = meterRepository.findById(updateMeterRequest.getIdMeter())
                .orElseThrow(() -> new ObjectNotFoundException("Meter not found: " + updateMeterRequest.getIdMeter()));
        System.out.println("meterFound" + meterFound.getMeterMark());
        meterFound.setMeterNumber(updateMeterRequest.getNumberMeter());
        meterFound.setMeterMark(updateMeterRequest.getMarkMeter());

        return getUpdateMeterResponse(meterRepository.save(meterFound));
    }

    @Override
    public Boolean deleteLogicMeter(Long idMeter) {
        WaterMeter meterToDelete = meterRepository.findById(idMeter)
                .orElseThrow(() -> new ObjectNotFoundException("Meter not found: " + idMeter));
        meterToDelete.setActive(false);
        return !meterRepository.save(meterToDelete).isActive();
    }

    @Override
    public Optional<WaterMeter> findByMeterNumber(String meterNumber) {
        return meterRepository.findByMeterNumber(meterNumber);
    }

    private static UpdateMeterResponse getUpdateMeterResponse(WaterMeter meterFound) {
        UpdateMeterResponse response = new UpdateMeterResponse();
        response.setIdMeter(meterFound.getMeterId());
        response.setMensaje("Medidor actualizado con éxito");
        return response;
    }
}
