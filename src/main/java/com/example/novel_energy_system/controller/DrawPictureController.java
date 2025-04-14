package com.example.novel_energy_system.controller;

import com.example.novel_energy_system.annotation.UserLoginToken;
import com.example.novel_energy_system.common.CodeMsg;
import com.example.novel_energy_system.common.Result;
import com.example.novel_energy_system.pojo.Picture;
import com.example.novel_energy_system.pojo.dto.PictureDto;
import com.example.novel_energy_system.service.PictureService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 图片控制器类，用于处理与图片相关的HTTP请求。
 * 提供添加、更新、删除和查询图片的接口。
 * @eo.api-type  http
 * @eo.groupName Picture
 * @eo.path
 */
@RestController
@RequestMapping("/picture")
public class DrawPictureController {

    @Autowired
    private PictureService pictureService;

    /**
     * 添加图片接口。
     * 根据传入的图片信息，调用服务层方法添加图片。
     * @param pictureDto 图片的信息，{用户名，描述等}
     * @return 返回添加结果，成功时返回包含成功信息的Result对象，失败时返回包含错误信息的Result对象。
     */

    @UserLoginToken
    @PostMapping("/add")
    public Result<String> addPicture(@RequestBody PictureDto pictureDto) {
        if (pictureDto == null || pictureDto.getPictureName() == null || pictureDto.getPictureName().trim().isEmpty()) {
            return Result.error(CodeMsg.PARAMETER_ISNULL, "Picture name must be provided");
        }
        if (pictureDto.getDescription() == null || pictureDto.getDescription().trim().isEmpty()) {
            return Result.error(CodeMsg.PARAMETER_ISNULL, "Description must be provided");
        }
        Picture picture = new Picture();
        picture.setName(pictureDto.getPictureName());
        picture.setDescription(pictureDto.getDescription());
        picture.setCreateTime(LocalDateTime.now());
        picture.setUpdateTime(LocalDateTime.now());

        int result = pictureService.addPicture(picture);
        if (result > 0) {
            return Result.success("Picture added successfully");
        } else {
            return Result.error(CodeMsg.SERVER_EXCEPTION, "Failed to add picture");
        }
    }

    /**
     * 更新图片接口。
     * 根据传入的图片信息，调用服务层方法更新图片。
     * @param pictureDto 图片实体对象，包含需要更新的图片信息，如图片名称、描述等。
     * @return 返回更新结果，成功时返回包含成功信息的Result对象，失败时返回包含错误信息的Result对象。
     */

    @UserLoginToken
    @PutMapping("/update")
    public Result<String> updatePicture(@RequestBody PictureDto pictureDto) {
        if (pictureDto == null || pictureDto.getId() == null) {
            return Result.error(CodeMsg.PARAMETER_ISNULL, "Picture ID must be provided");
        }
        if (pictureDto.getPictureName() == null || pictureDto.getPictureName().trim().isEmpty()) {
            return Result.error(CodeMsg.PARAMETER_ISNULL, "Picture name must be provided");
        }
        if (pictureDto.getDescription() == null || pictureDto.getDescription().trim().isEmpty()) {
            return Result.error(CodeMsg.PARAMETER_ISNULL, "Description must be provided");
        }

        Picture picture = new Picture();
        picture.setId(pictureDto.getId());
        picture.setName(pictureDto.getPictureName());
        picture.setDescription(pictureDto.getDescription());
        picture.setUpdateTime(LocalDateTime.now());

        int result = pictureService.updatePicture(picture);
        if (result > 0) {
            return Result.success("Picture updated successfully");
        } else {
            return Result.error(CodeMsg.SERVER_EXCEPTION, "Failed to update picture");
        }
    }

    /**
     * 删除图片接口。
     * 根据传入的图片ID，调用服务层方法删除图片。
     * @param id 图片的唯一标识ID。
     * @return 返回删除结果，成功时返回包含成功信息的Result对象，失败时返回包含错误信息的Result对象。
     */

    @UserLoginToken
    @DeleteMapping("/delete/{id}")
    public Result<String> deletePicture(@PathVariable int id) {
        int result = pictureService.deletePicture(id);
        if (result > 0) {
            return Result.success("Picture deleted successfully");
        } else {
            return Result.error(CodeMsg.SERVER_EXCEPTION, "Failed to delete picture");
        }
    }

    /**
     * 查询图片列表接口。
     * 根据传入的分页参数，调用服务层方法查询图片列表。
     * @param pageNum 当前页码。
     * @param pageSize 每页显示的记录数。
     * @return 返回查询结果，包含分页信息和图片列表数据的Result对象。
     */

    @UserLoginToken
    @GetMapping("/list")
    public Result<PageInfo<Picture>> getPictureList(@RequestParam int pageNum, @RequestParam int pageSize) {
        PageInfo<Picture> pageInfo = pictureService.selectPicture(pageNum, pageSize);
        return Result.success(pageInfo);
    }
}