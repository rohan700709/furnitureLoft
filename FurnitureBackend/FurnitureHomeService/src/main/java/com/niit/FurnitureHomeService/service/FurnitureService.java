package com.niit.FurnitureHomeService.service;

import com.niit.FurnitureHomeService.exception.FurnitureAlreadyExistsException;
import com.niit.FurnitureHomeService.exception.FurnitureNotFoundException;
import com.niit.FurnitureHomeService.model.Furniture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FurnitureService {
   Furniture saveFurniture(Furniture furniture) throws FurnitureAlreadyExistsException;
   List<Furniture> getAllFurniture() throws FurnitureNotFoundException;
   boolean deleteFurniture(int furnitureId) throws FurnitureNotFoundException;
   Furniture updateFurniture(Furniture furniture, int furnitureId) throws FurnitureNotFoundException;
   Furniture getOneFurniture(int furnitureId) throws FurnitureNotFoundException;
   BufferedImage byteArrayToImage(byte[] imageData) throws IOException;
}
