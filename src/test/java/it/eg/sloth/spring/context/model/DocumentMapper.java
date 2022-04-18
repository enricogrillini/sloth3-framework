package it.eg.sloth.spring.context.model;

import it.eg.sloth.api.common.PojoMapper;
import it.eg.sloth.api.decodemap.DecodeValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(uses = PojoMapper.class)
public interface DocumentMapper  {

    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    @Mapping(source = "idDocument", target = "code")
    @Mapping(source = "name", target = "description")
    @Mapping(source = "flagActive", target = "valid")
    DecodeValue<Integer> toDecodeValue(Document pojo);

    List<DecodeValue<Integer>> toDecodeMap(List<Document> list);

}