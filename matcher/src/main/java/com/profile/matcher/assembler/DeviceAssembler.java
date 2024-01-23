package com.profile.matcher.assembler;

import com.profile.matcher.arhitecture.BaseAssembler;
import com.profile.matcher.dto.player.DeviceDto;
import com.profile.matcher.entity.player.Device;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeviceAssembler extends BaseAssembler<Device, DeviceDto> {


    @Override
    public DeviceDto toResource(Device device) {
        DeviceDto deviceDto = new DeviceDto();

        if (null != device) {
            deviceDto.setId(device.getIdDevice());
            deviceDto.setModel(device.getModel());
            deviceDto.setCarrier(device.getCarrier());
            deviceDto.setFirmware(device.getFirmware());
        }
        return deviceDto;
    }

    @Override
    public List<DeviceDto> toCollectionResource(List<Device> devices) {
        List<DeviceDto> deviceDtos = new ArrayList<>();

        if (!CollectionUtils.isEmpty(devices)) {
            for (Device device : devices) {
                deviceDtos.add(toResource(device));
            }
        }
        return deviceDtos;
    }
}
