package com.allen.project.epis;

import java.io.Serializable;
import java.util.Date;

public class TiEpisBackinterfaceCurrent implements Serializable {
    private Long id;

    private Integer createBy;

    private Date createDate;

    private Integer updateBy;

    private Date updateDate;

    private String recordType;//NO.1 

    private String partNumber;//NO.2

    private String productionPlant;//NO.3

    private String kindOfMaterial;//NO.4

    private String availableStock;//NO.5

    private String totalConsumptionCurrentYear;//NO.6

    private String totalConsumption;//NO.7

    private String location;//NO.8

    private String lifeCircle;//NO.9

    private String dispositionCode;//NO.10

    private String endOfSupplyTime;//NO.11

    private String surfaceTreat;//NO.12

    private String premiumCode;//NO.13

    private String fertigungsortS;//NO.14

    private String statussymbolT;//NO.15

    private String gesamtLagernull;//NO.16

    private String kennzeichen;//NO.17

    private String materialGroup;//NO.18

    private String entfallW;//NO.19

    private String lagerBestand;//NO.20

    private String price;//NO.21

    private String priceEuro;//NO.22

    private String priceDate;//NO.23

    private String discountGroup;//NO.24

    private String priceVParts;//NO.25

    private String priceVPartsEuro;//NO.26

    private String priceVPartsDate;//NO.27

    private String materialGroup4;//NO.28

    private String priceYParts;//NO.29

    private String priceYPartsEuro;//NO.30

    private String priceYPartsDate;//NO.31

    private String dicountGroup;//NO.32

    private String estimatedPrice;//NO.33

    private String weight;//NO.34

    private String weightUnit;//NO.35

    private String dimension;//NO.36

    private String estimatedWeight;//NO.37

    private String estimatedDimension;//NO.38

    private String materialPlaner;//NO.39

    private String subjectAreaPricing;//NO.40

    private String purchaserGroup;//NO.41

    private String stockZero;//NO.42

    private String compoundSupplier;//NO.43

    private String dangerousGoodsIndicator;//NO.44

    private String environmentallyRelevant;//NO.45

    private String hazardousMaterialNumbe;//NO.46

    private String minimumRemainingShelfLife;//NO.47

    private String shelfLifeExpirationDate;//NO.48

    private String calculationOfSled;//NO.49

    private String storagePercentage;//NO.50

    private String totalShelfLife;//NO.51

    private String dangerGoodKey;//NO.52

    private String maximumStoragePeriod;//NO.53

    private String unitMaximumStoragePeriod;//NO.54

    private String postponedSpareStartingDate;//NO.55

    private String lockOfAvailabilityCheck;//NO.56

    private String numberXPart;//NO.57

    private String itemType;//NO.58

    private String componentType;//NO.59

    private String limitedStoringTime;//NO.60

    private String dangerousGoods;//NO.61

    private String classOfFireDanger;//NO.62

    private String ballBearingFlag;//NO.63

    private String packagingNumber;//NO.64

    private String manufacturerPartNo;//NO.65

    private String productHierarchie;//NO.66

    private String dispocode;//NO.67

    private String idForProduct;//NO.68

    private String alternativePartno;//NO.69

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getProductionPlant() {
        return productionPlant;
    }

    public void setProductionPlant(String productionPlant) {
        this.productionPlant = productionPlant;
    }

    public String getKindOfMaterial() {
        return kindOfMaterial;
    }

    public void setKindOfMaterial(String kindOfMaterial) {
        this.kindOfMaterial = kindOfMaterial;
    }

    public String getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(String availableStock) {
        this.availableStock = availableStock;
    }

    public String getTotalConsumptionCurrentYear() {
        return totalConsumptionCurrentYear;
    }

    public void setTotalConsumptionCurrentYear(String totalConsumptionCurrentYear) {
        this.totalConsumptionCurrentYear = totalConsumptionCurrentYear;
    }

    public String getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(String totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLifeCircle() {
        return lifeCircle;
    }

    public void setLifeCircle(String lifeCircle) {
        this.lifeCircle = lifeCircle;
    }

    public String getDispositionCode() {
        return dispositionCode;
    }

    public void setDispositionCode(String dispositionCode) {
        this.dispositionCode = dispositionCode;
    }

    public String getEndOfSupplyTime() {
        return endOfSupplyTime;
    }

    public void setEndOfSupplyTime(String endOfSupplyTime) {
        this.endOfSupplyTime = endOfSupplyTime;
    }

    public String getSurfaceTreat() {
        return surfaceTreat;
    }

    public void setSurfaceTreat(String surfaceTreat) {
        this.surfaceTreat = surfaceTreat;
    }

    public String getPremiumCode() {
        return premiumCode;
    }

    public void setPremiumCode(String premiumCode) {
        this.premiumCode = premiumCode;
    }

    public String getFertigungsortS() {
        return fertigungsortS;
    }

    public void setFertigungsortS(String fertigungsortS) {
        this.fertigungsortS = fertigungsortS;
    }

    public String getStatussymbolT() {
        return statussymbolT;
    }

    public void setStatussymbolT(String statussymbolT) {
        this.statussymbolT = statussymbolT;
    }

    public String getGesamtLagernull() {
        return gesamtLagernull;
    }

    public void setGesamtLagernull(String gesamtLagernull) {
        this.gesamtLagernull = gesamtLagernull;
    }

    public String getKennzeichen() {
        return kennzeichen;
    }

    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }

    public String getMaterialGroup() {
        return materialGroup;
    }

    public void setMaterialGroup(String materialGroup) {
        this.materialGroup = materialGroup;
    }

    public String getEntfallW() {
        return entfallW;
    }

    public void setEntfallW(String entfallW) {
        this.entfallW = entfallW;
    }

    public String getLagerBestand() {
        return lagerBestand;
    }

    public void setLagerBestand(String lagerBestand) {
        this.lagerBestand = lagerBestand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceEuro() {
        return priceEuro;
    }

    public void setPriceEuro(String priceEuro) {
        this.priceEuro = priceEuro;
    }

    public String getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(String priceDate) {
        this.priceDate = priceDate;
    }

    public String getDiscountGroup() {
        return discountGroup;
    }

    public void setDiscountGroup(String discountGroup) {
        this.discountGroup = discountGroup;
    }

    public String getPriceVParts() {
        return priceVParts;
    }

    public void setPriceVParts(String priceVParts) {
        this.priceVParts = priceVParts;
    }

    public String getPriceVPartsEuro() {
        return priceVPartsEuro;
    }

    public void setPriceVPartsEuro(String priceVPartsEuro) {
        this.priceVPartsEuro = priceVPartsEuro;
    }

    public String getPriceVPartsDate() {
        return priceVPartsDate;
    }

    public void setPriceVPartsDate(String priceVPartsDate) {
        this.priceVPartsDate = priceVPartsDate;
    }

    public String getMaterialGroup4() {
        return materialGroup4;
    }

    public void setMaterialGroup4(String materialGroup4) {
        this.materialGroup4 = materialGroup4;
    }

    public String getPriceYParts() {
        return priceYParts;
    }

    public void setPriceYParts(String priceYParts) {
        this.priceYParts = priceYParts;
    }

    public String getPriceYPartsEuro() {
        return priceYPartsEuro;
    }

    public void setPriceYPartsEuro(String priceYPartsEuro) {
        this.priceYPartsEuro = priceYPartsEuro;
    }

    public String getPriceYPartsDate() {
        return priceYPartsDate;
    }

    public void setPriceYPartsDate(String priceYPartsDate) {
        this.priceYPartsDate = priceYPartsDate;
    }

    public String getDicountGroup() {
        return dicountGroup;
    }

    public void setDicountGroup(String dicountGroup) {
        this.dicountGroup = dicountGroup;
    }

    public String getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(String estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getEstimatedWeight() {
        return estimatedWeight;
    }

    public void setEstimatedWeight(String estimatedWeight) {
        this.estimatedWeight = estimatedWeight;
    }

    public String getEstimatedDimension() {
        return estimatedDimension;
    }

    public void setEstimatedDimension(String estimatedDimension) {
        this.estimatedDimension = estimatedDimension;
    }

    public String getMaterialPlaner() {
        return materialPlaner;
    }

    public void setMaterialPlaner(String materialPlaner) {
        this.materialPlaner = materialPlaner;
    }

    public String getSubjectAreaPricing() {
        return subjectAreaPricing;
    }

    public void setSubjectAreaPricing(String subjectAreaPricing) {
        this.subjectAreaPricing = subjectAreaPricing;
    }

    public String getPurchaserGroup() {
        return purchaserGroup;
    }

    public void setPurchaserGroup(String purchaserGroup) {
        this.purchaserGroup = purchaserGroup;
    }

    public String getStockZero() {
        return stockZero;
    }

    public void setStockZero(String stockZero) {
        this.stockZero = stockZero;
    }

    public String getCompoundSupplier() {
        return compoundSupplier;
    }

    public void setCompoundSupplier(String compoundSupplier) {
        this.compoundSupplier = compoundSupplier;
    }

    public String getDangerousGoodsIndicator() {
        return dangerousGoodsIndicator;
    }

    public void setDangerousGoodsIndicator(String dangerousGoodsIndicator) {
        this.dangerousGoodsIndicator = dangerousGoodsIndicator;
    }

    public String getEnvironmentallyRelevant() {
        return environmentallyRelevant;
    }

    public void setEnvironmentallyRelevant(String environmentallyRelevant) {
        this.environmentallyRelevant = environmentallyRelevant;
    }

    public String getHazardousMaterialNumbe() {
        return hazardousMaterialNumbe;
    }

    public void setHazardousMaterialNumbe(String hazardousMaterialNumbe) {
        this.hazardousMaterialNumbe = hazardousMaterialNumbe;
    }

    public String getMinimumRemainingShelfLife() {
        return minimumRemainingShelfLife;
    }

    public void setMinimumRemainingShelfLife(String minimumRemainingShelfLife) {
        this.minimumRemainingShelfLife = minimumRemainingShelfLife;
    }

    public String getShelfLifeExpirationDate() {
        return shelfLifeExpirationDate;
    }

    public void setShelfLifeExpirationDate(String shelfLifeExpirationDate) {
        this.shelfLifeExpirationDate = shelfLifeExpirationDate;
    }

    public String getCalculationOfSled() {
        return calculationOfSled;
    }

    public void setCalculationOfSled(String calculationOfSled) {
        this.calculationOfSled = calculationOfSled;
    }

    public String getStoragePercentage() {
        return storagePercentage;
    }

    public void setStoragePercentage(String storagePercentage) {
        this.storagePercentage = storagePercentage;
    }

    public String getTotalShelfLife() {
        return totalShelfLife;
    }

    public void setTotalShelfLife(String totalShelfLife) {
        this.totalShelfLife = totalShelfLife;
    }

    public String getDangerGoodKey() {
        return dangerGoodKey;
    }

    public void setDangerGoodKey(String dangerGoodKey) {
        this.dangerGoodKey = dangerGoodKey;
    }

    public String getMaximumStoragePeriod() {
        return maximumStoragePeriod;
    }

    public void setMaximumStoragePeriod(String maximumStoragePeriod) {
        this.maximumStoragePeriod = maximumStoragePeriod;
    }

    public String getUnitMaximumStoragePeriod() {
        return unitMaximumStoragePeriod;
    }

    public void setUnitMaximumStoragePeriod(String unitMaximumStoragePeriod) {
        this.unitMaximumStoragePeriod = unitMaximumStoragePeriod;
    }

    public String getPostponedSpareStartingDate() {
        return postponedSpareStartingDate;
    }

    public void setPostponedSpareStartingDate(String postponedSpareStartingDate) {
        this.postponedSpareStartingDate = postponedSpareStartingDate;
    }

    public String getLockOfAvailabilityCheck() {
        return lockOfAvailabilityCheck;
    }

    public void setLockOfAvailabilityCheck(String lockOfAvailabilityCheck) {
        this.lockOfAvailabilityCheck = lockOfAvailabilityCheck;
    }

    public String getNumberXPart() {
        return numberXPart;
    }

    public void setNumberXPart(String numberXPart) {
        this.numberXPart = numberXPart;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getLimitedStoringTime() {
        return limitedStoringTime;
    }

    public void setLimitedStoringTime(String limitedStoringTime) {
        this.limitedStoringTime = limitedStoringTime;
    }

    public String getDangerousGoods() {
        return dangerousGoods;
    }

    public void setDangerousGoods(String dangerousGoods) {
        this.dangerousGoods = dangerousGoods;
    }

    public String getClassOfFireDanger() {
        return classOfFireDanger;
    }

    public void setClassOfFireDanger(String classOfFireDanger) {
        this.classOfFireDanger = classOfFireDanger;
    }

    public String getBallBearingFlag() {
        return ballBearingFlag;
    }

    public void setBallBearingFlag(String ballBearingFlag) {
        this.ballBearingFlag = ballBearingFlag;
    }

    public String getPackagingNumber() {
        return packagingNumber;
    }

    public void setPackagingNumber(String packagingNumber) {
        this.packagingNumber = packagingNumber;
    }

    public String getManufacturerPartNo() {
        return manufacturerPartNo;
    }

    public void setManufacturerPartNo(String manufacturerPartNo) {
        this.manufacturerPartNo = manufacturerPartNo;
    }

    public String getProductHierarchie() {
        return productHierarchie;
    }

    public void setProductHierarchie(String productHierarchie) {
        this.productHierarchie = productHierarchie;
    }

    public String getDispocode() {
        return dispocode;
    }

    public void setDispocode(String dispocode) {
        this.dispocode = dispocode;
    }

    public String getIdForProduct() {
        return idForProduct;
    }

    public void setIdForProduct(String idForProduct) {
        this.idForProduct = idForProduct;
    }

    public String getAlternativePartno() {
        return alternativePartno;
    }

    public void setAlternativePartno(String alternativePartno) {
        this.alternativePartno = alternativePartno;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TiEpisBackinterfaceCurrent other = (TiEpisBackinterfaceCurrent) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getRecordType() == null ? other.getRecordType() == null : this.getRecordType().equals(other.getRecordType()))
            && (this.getPartNumber() == null ? other.getPartNumber() == null : this.getPartNumber().equals(other.getPartNumber()))
            && (this.getProductionPlant() == null ? other.getProductionPlant() == null : this.getProductionPlant().equals(other.getProductionPlant()))
            && (this.getKindOfMaterial() == null ? other.getKindOfMaterial() == null : this.getKindOfMaterial().equals(other.getKindOfMaterial()))
            && (this.getAvailableStock() == null ? other.getAvailableStock() == null : this.getAvailableStock().equals(other.getAvailableStock()))
            && (this.getTotalConsumptionCurrentYear() == null ? other.getTotalConsumptionCurrentYear() == null : this.getTotalConsumptionCurrentYear().equals(other.getTotalConsumptionCurrentYear()))
            && (this.getTotalConsumption() == null ? other.getTotalConsumption() == null : this.getTotalConsumption().equals(other.getTotalConsumption()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getLifeCircle() == null ? other.getLifeCircle() == null : this.getLifeCircle().equals(other.getLifeCircle()))
            && (this.getDispositionCode() == null ? other.getDispositionCode() == null : this.getDispositionCode().equals(other.getDispositionCode()))
            && (this.getEndOfSupplyTime() == null ? other.getEndOfSupplyTime() == null : this.getEndOfSupplyTime().equals(other.getEndOfSupplyTime()))
            && (this.getSurfaceTreat() == null ? other.getSurfaceTreat() == null : this.getSurfaceTreat().equals(other.getSurfaceTreat()))
            && (this.getPremiumCode() == null ? other.getPremiumCode() == null : this.getPremiumCode().equals(other.getPremiumCode()))
            && (this.getFertigungsortS() == null ? other.getFertigungsortS() == null : this.getFertigungsortS().equals(other.getFertigungsortS()))
            && (this.getStatussymbolT() == null ? other.getStatussymbolT() == null : this.getStatussymbolT().equals(other.getStatussymbolT()))
            && (this.getGesamtLagernull() == null ? other.getGesamtLagernull() == null : this.getGesamtLagernull().equals(other.getGesamtLagernull()))
            && (this.getKennzeichen() == null ? other.getKennzeichen() == null : this.getKennzeichen().equals(other.getKennzeichen()))
            && (this.getMaterialGroup() == null ? other.getMaterialGroup() == null : this.getMaterialGroup().equals(other.getMaterialGroup()))
            && (this.getEntfallW() == null ? other.getEntfallW() == null : this.getEntfallW().equals(other.getEntfallW()))
            && (this.getLagerBestand() == null ? other.getLagerBestand() == null : this.getLagerBestand().equals(other.getLagerBestand()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getPriceEuro() == null ? other.getPriceEuro() == null : this.getPriceEuro().equals(other.getPriceEuro()))
            && (this.getPriceDate() == null ? other.getPriceDate() == null : this.getPriceDate().equals(other.getPriceDate()))
            && (this.getDiscountGroup() == null ? other.getDiscountGroup() == null : this.getDiscountGroup().equals(other.getDiscountGroup()))
            && (this.getPriceVParts() == null ? other.getPriceVParts() == null : this.getPriceVParts().equals(other.getPriceVParts()))
            && (this.getPriceVPartsEuro() == null ? other.getPriceVPartsEuro() == null : this.getPriceVPartsEuro().equals(other.getPriceVPartsEuro()))
            && (this.getPriceVPartsDate() == null ? other.getPriceVPartsDate() == null : this.getPriceVPartsDate().equals(other.getPriceVPartsDate()))
            && (this.getMaterialGroup4() == null ? other.getMaterialGroup4() == null : this.getMaterialGroup4().equals(other.getMaterialGroup4()))
            && (this.getPriceYParts() == null ? other.getPriceYParts() == null : this.getPriceYParts().equals(other.getPriceYParts()))
            && (this.getPriceYPartsEuro() == null ? other.getPriceYPartsEuro() == null : this.getPriceYPartsEuro().equals(other.getPriceYPartsEuro()))
            && (this.getPriceYPartsDate() == null ? other.getPriceYPartsDate() == null : this.getPriceYPartsDate().equals(other.getPriceYPartsDate()))
            && (this.getDicountGroup() == null ? other.getDicountGroup() == null : this.getDicountGroup().equals(other.getDicountGroup()))
            && (this.getEstimatedPrice() == null ? other.getEstimatedPrice() == null : this.getEstimatedPrice().equals(other.getEstimatedPrice()))
            && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()))
            && (this.getWeightUnit() == null ? other.getWeightUnit() == null : this.getWeightUnit().equals(other.getWeightUnit()))
            && (this.getDimension() == null ? other.getDimension() == null : this.getDimension().equals(other.getDimension()))
            && (this.getEstimatedWeight() == null ? other.getEstimatedWeight() == null : this.getEstimatedWeight().equals(other.getEstimatedWeight()))
            && (this.getEstimatedDimension() == null ? other.getEstimatedDimension() == null : this.getEstimatedDimension().equals(other.getEstimatedDimension()))
            && (this.getMaterialPlaner() == null ? other.getMaterialPlaner() == null : this.getMaterialPlaner().equals(other.getMaterialPlaner()))
            && (this.getSubjectAreaPricing() == null ? other.getSubjectAreaPricing() == null : this.getSubjectAreaPricing().equals(other.getSubjectAreaPricing()))
            && (this.getPurchaserGroup() == null ? other.getPurchaserGroup() == null : this.getPurchaserGroup().equals(other.getPurchaserGroup()))
            && (this.getStockZero() == null ? other.getStockZero() == null : this.getStockZero().equals(other.getStockZero()))
            && (this.getCompoundSupplier() == null ? other.getCompoundSupplier() == null : this.getCompoundSupplier().equals(other.getCompoundSupplier()))
            && (this.getDangerousGoodsIndicator() == null ? other.getDangerousGoodsIndicator() == null : this.getDangerousGoodsIndicator().equals(other.getDangerousGoodsIndicator()))
            && (this.getEnvironmentallyRelevant() == null ? other.getEnvironmentallyRelevant() == null : this.getEnvironmentallyRelevant().equals(other.getEnvironmentallyRelevant()))
            && (this.getHazardousMaterialNumbe() == null ? other.getHazardousMaterialNumbe() == null : this.getHazardousMaterialNumbe().equals(other.getHazardousMaterialNumbe()))
            && (this.getMinimumRemainingShelfLife() == null ? other.getMinimumRemainingShelfLife() == null : this.getMinimumRemainingShelfLife().equals(other.getMinimumRemainingShelfLife()))
            && (this.getShelfLifeExpirationDate() == null ? other.getShelfLifeExpirationDate() == null : this.getShelfLifeExpirationDate().equals(other.getShelfLifeExpirationDate()))
            && (this.getCalculationOfSled() == null ? other.getCalculationOfSled() == null : this.getCalculationOfSled().equals(other.getCalculationOfSled()))
            && (this.getStoragePercentage() == null ? other.getStoragePercentage() == null : this.getStoragePercentage().equals(other.getStoragePercentage()))
            && (this.getTotalShelfLife() == null ? other.getTotalShelfLife() == null : this.getTotalShelfLife().equals(other.getTotalShelfLife()))
            && (this.getDangerGoodKey() == null ? other.getDangerGoodKey() == null : this.getDangerGoodKey().equals(other.getDangerGoodKey()))
            && (this.getMaximumStoragePeriod() == null ? other.getMaximumStoragePeriod() == null : this.getMaximumStoragePeriod().equals(other.getMaximumStoragePeriod()))
            && (this.getUnitMaximumStoragePeriod() == null ? other.getUnitMaximumStoragePeriod() == null : this.getUnitMaximumStoragePeriod().equals(other.getUnitMaximumStoragePeriod()))
            && (this.getPostponedSpareStartingDate() == null ? other.getPostponedSpareStartingDate() == null : this.getPostponedSpareStartingDate().equals(other.getPostponedSpareStartingDate()))
            && (this.getLockOfAvailabilityCheck() == null ? other.getLockOfAvailabilityCheck() == null : this.getLockOfAvailabilityCheck().equals(other.getLockOfAvailabilityCheck()))
            && (this.getNumberXPart() == null ? other.getNumberXPart() == null : this.getNumberXPart().equals(other.getNumberXPart()))
            && (this.getItemType() == null ? other.getItemType() == null : this.getItemType().equals(other.getItemType()))
            && (this.getComponentType() == null ? other.getComponentType() == null : this.getComponentType().equals(other.getComponentType()))
            && (this.getLimitedStoringTime() == null ? other.getLimitedStoringTime() == null : this.getLimitedStoringTime().equals(other.getLimitedStoringTime()))
            && (this.getDangerousGoods() == null ? other.getDangerousGoods() == null : this.getDangerousGoods().equals(other.getDangerousGoods()))
            && (this.getClassOfFireDanger() == null ? other.getClassOfFireDanger() == null : this.getClassOfFireDanger().equals(other.getClassOfFireDanger()))
            && (this.getBallBearingFlag() == null ? other.getBallBearingFlag() == null : this.getBallBearingFlag().equals(other.getBallBearingFlag()))
            && (this.getPackagingNumber() == null ? other.getPackagingNumber() == null : this.getPackagingNumber().equals(other.getPackagingNumber()))
            && (this.getManufacturerPartNo() == null ? other.getManufacturerPartNo() == null : this.getManufacturerPartNo().equals(other.getManufacturerPartNo()))
            && (this.getProductHierarchie() == null ? other.getProductHierarchie() == null : this.getProductHierarchie().equals(other.getProductHierarchie()))
            && (this.getDispocode() == null ? other.getDispocode() == null : this.getDispocode().equals(other.getDispocode()))
            && (this.getIdForProduct() == null ? other.getIdForProduct() == null : this.getIdForProduct().equals(other.getIdForProduct()))
            && (this.getAlternativePartno() == null ? other.getAlternativePartno() == null : this.getAlternativePartno().equals(other.getAlternativePartno()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getRecordType() == null) ? 0 : getRecordType().hashCode());
        result = prime * result + ((getPartNumber() == null) ? 0 : getPartNumber().hashCode());
        result = prime * result + ((getProductionPlant() == null) ? 0 : getProductionPlant().hashCode());
        result = prime * result + ((getKindOfMaterial() == null) ? 0 : getKindOfMaterial().hashCode());
        result = prime * result + ((getAvailableStock() == null) ? 0 : getAvailableStock().hashCode());
        result = prime * result + ((getTotalConsumptionCurrentYear() == null) ? 0 : getTotalConsumptionCurrentYear().hashCode());
        result = prime * result + ((getTotalConsumption() == null) ? 0 : getTotalConsumption().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getLifeCircle() == null) ? 0 : getLifeCircle().hashCode());
        result = prime * result + ((getDispositionCode() == null) ? 0 : getDispositionCode().hashCode());
        result = prime * result + ((getEndOfSupplyTime() == null) ? 0 : getEndOfSupplyTime().hashCode());
        result = prime * result + ((getSurfaceTreat() == null) ? 0 : getSurfaceTreat().hashCode());
        result = prime * result + ((getPremiumCode() == null) ? 0 : getPremiumCode().hashCode());
        result = prime * result + ((getFertigungsortS() == null) ? 0 : getFertigungsortS().hashCode());
        result = prime * result + ((getStatussymbolT() == null) ? 0 : getStatussymbolT().hashCode());
        result = prime * result + ((getGesamtLagernull() == null) ? 0 : getGesamtLagernull().hashCode());
        result = prime * result + ((getKennzeichen() == null) ? 0 : getKennzeichen().hashCode());
        result = prime * result + ((getMaterialGroup() == null) ? 0 : getMaterialGroup().hashCode());
        result = prime * result + ((getEntfallW() == null) ? 0 : getEntfallW().hashCode());
        result = prime * result + ((getLagerBestand() == null) ? 0 : getLagerBestand().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getPriceEuro() == null) ? 0 : getPriceEuro().hashCode());
        result = prime * result + ((getPriceDate() == null) ? 0 : getPriceDate().hashCode());
        result = prime * result + ((getDiscountGroup() == null) ? 0 : getDiscountGroup().hashCode());
        result = prime * result + ((getPriceVParts() == null) ? 0 : getPriceVParts().hashCode());
        result = prime * result + ((getPriceVPartsEuro() == null) ? 0 : getPriceVPartsEuro().hashCode());
        result = prime * result + ((getPriceVPartsDate() == null) ? 0 : getPriceVPartsDate().hashCode());
        result = prime * result + ((getMaterialGroup4() == null) ? 0 : getMaterialGroup4().hashCode());
        result = prime * result + ((getPriceYParts() == null) ? 0 : getPriceYParts().hashCode());
        result = prime * result + ((getPriceYPartsEuro() == null) ? 0 : getPriceYPartsEuro().hashCode());
        result = prime * result + ((getPriceYPartsDate() == null) ? 0 : getPriceYPartsDate().hashCode());
        result = prime * result + ((getDicountGroup() == null) ? 0 : getDicountGroup().hashCode());
        result = prime * result + ((getEstimatedPrice() == null) ? 0 : getEstimatedPrice().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        result = prime * result + ((getWeightUnit() == null) ? 0 : getWeightUnit().hashCode());
        result = prime * result + ((getDimension() == null) ? 0 : getDimension().hashCode());
        result = prime * result + ((getEstimatedWeight() == null) ? 0 : getEstimatedWeight().hashCode());
        result = prime * result + ((getEstimatedDimension() == null) ? 0 : getEstimatedDimension().hashCode());
        result = prime * result + ((getMaterialPlaner() == null) ? 0 : getMaterialPlaner().hashCode());
        result = prime * result + ((getSubjectAreaPricing() == null) ? 0 : getSubjectAreaPricing().hashCode());
        result = prime * result + ((getPurchaserGroup() == null) ? 0 : getPurchaserGroup().hashCode());
        result = prime * result + ((getStockZero() == null) ? 0 : getStockZero().hashCode());
        result = prime * result + ((getCompoundSupplier() == null) ? 0 : getCompoundSupplier().hashCode());
        result = prime * result + ((getDangerousGoodsIndicator() == null) ? 0 : getDangerousGoodsIndicator().hashCode());
        result = prime * result + ((getEnvironmentallyRelevant() == null) ? 0 : getEnvironmentallyRelevant().hashCode());
        result = prime * result + ((getHazardousMaterialNumbe() == null) ? 0 : getHazardousMaterialNumbe().hashCode());
        result = prime * result + ((getMinimumRemainingShelfLife() == null) ? 0 : getMinimumRemainingShelfLife().hashCode());
        result = prime * result + ((getShelfLifeExpirationDate() == null) ? 0 : getShelfLifeExpirationDate().hashCode());
        result = prime * result + ((getCalculationOfSled() == null) ? 0 : getCalculationOfSled().hashCode());
        result = prime * result + ((getStoragePercentage() == null) ? 0 : getStoragePercentage().hashCode());
        result = prime * result + ((getTotalShelfLife() == null) ? 0 : getTotalShelfLife().hashCode());
        result = prime * result + ((getDangerGoodKey() == null) ? 0 : getDangerGoodKey().hashCode());
        result = prime * result + ((getMaximumStoragePeriod() == null) ? 0 : getMaximumStoragePeriod().hashCode());
        result = prime * result + ((getUnitMaximumStoragePeriod() == null) ? 0 : getUnitMaximumStoragePeriod().hashCode());
        result = prime * result + ((getPostponedSpareStartingDate() == null) ? 0 : getPostponedSpareStartingDate().hashCode());
        result = prime * result + ((getLockOfAvailabilityCheck() == null) ? 0 : getLockOfAvailabilityCheck().hashCode());
        result = prime * result + ((getNumberXPart() == null) ? 0 : getNumberXPart().hashCode());
        result = prime * result + ((getItemType() == null) ? 0 : getItemType().hashCode());
        result = prime * result + ((getComponentType() == null) ? 0 : getComponentType().hashCode());
        result = prime * result + ((getLimitedStoringTime() == null) ? 0 : getLimitedStoringTime().hashCode());
        result = prime * result + ((getDangerousGoods() == null) ? 0 : getDangerousGoods().hashCode());
        result = prime * result + ((getClassOfFireDanger() == null) ? 0 : getClassOfFireDanger().hashCode());
        result = prime * result + ((getBallBearingFlag() == null) ? 0 : getBallBearingFlag().hashCode());
        result = prime * result + ((getPackagingNumber() == null) ? 0 : getPackagingNumber().hashCode());
        result = prime * result + ((getManufacturerPartNo() == null) ? 0 : getManufacturerPartNo().hashCode());
        result = prime * result + ((getProductHierarchie() == null) ? 0 : getProductHierarchie().hashCode());
        result = prime * result + ((getDispocode() == null) ? 0 : getDispocode().hashCode());
        result = prime * result + ((getIdForProduct() == null) ? 0 : getIdForProduct().hashCode());
        result = prime * result + ((getAlternativePartno() == null) ? 0 : getAlternativePartno().hashCode());
        return result;
    }
}